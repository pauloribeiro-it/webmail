package br.com.webmail.domain.email;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.application.NavigationHandler;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Named;

import org.primefaces.event.MenuActionEvent;
import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.DefaultSubMenu;
import org.primefaces.model.menu.MenuModel;

import br.com.webmail.domain.filtro.Filtro;
import br.com.webmail.domain.filtro.FiltroService;
import br.com.webmail.domain.usuario.Usuario;
import br.com.webmail.domain.usuario.UsuarioService;
import br.com.webmail.enums.EnumFiltro;
import br.com.webmail.util.WebmailUtil;

@SessionScoped
@Named("emailMB")
public class EmailMB implements Serializable {
	private static final long serialVersionUID = 5052247699927310089L;
	private MenuModel simpleMenuModel = new DefaultMenuModel();
	private DefaultSubMenu submenu;
	private List<Email> emails;
	
	private Email email;

	private Usuario usuario;
	
	@EJB
	private FiltroService filtroService;

	@EJB
	private EmailService emailService;

	@EJB
	private UsuarioService usuarioService;
	
	private String destinatario;

	private Email emailSelecionado;
	
	private List<Email> emailsSelecionados;
	
	private Filtro filtroSelecionado = new Filtro();
	
	public EmailMB() {

	}

	@PostConstruct
	public void configuraPagina() {
		email = new Email();
		usuario = WebmailUtil.getUsuarioSessao();
		this.submenu = new DefaultSubMenu("Emails");
		simpleMenuModel.addElement(submenu);
		filtroSelecionado = new Filtro(EnumFiltro.CAIXA_ENTRADA.getValor(), EnumFiltro.CAIXA_ENTRADA.getDescricao());
		criaNovoEmailOpcaoMenu();
		configuraFiltrosPersonalizados();
		obtemEmailsCaixaDeEntrada();
	}

	private void criaNovoEmailOpcaoMenu() {
		DefaultMenuItem itemMenu = new DefaultMenuItem("Novo email");
		itemMenu.setUrl("/user/novoEmail.jsf");
		submenu.addElement(itemMenu);
	}

	private void obtemEmailsCaixaDeEntrada() {
		this.emails = emailService.obtemEmailsPorUsuarioEFiltro(usuario, EnumFiltro.CAIXA_ENTRADA.getValor());
	}

	private void configuraFiltrosPersonalizados() {
		List<Filtro> filtrosResult = filtroService.obtemFiltrosUsuario(usuario);
		for (Filtro filtro : filtrosResult) {
			DefaultMenuItem itemMenu = new DefaultMenuItem(filtro.getNome());
			itemMenu.setCommand("#{emailMB.retornaEmails}");
			itemMenu.setId(filtro.getId().toString());
			itemMenu.setParam("idMenu", filtro.getId());
			submenu.addElement(itemMenu);
		}
	}

	public void retornaEmails(ActionEvent e) {
		MenuActionEvent menuActionEvent = (MenuActionEvent) e;
		Long idFiltro = Long.parseLong(menuActionEvent.getMenuItem().getParams().get("idMenu").get(0));
		configuraFiltroSelecionado(EnumFiltro.obtemFiltroPorId(idFiltro));
		emails = emailService.obtemEmailsPorUsuarioEFiltro(usuario, idFiltro);
		NavigationHandler nh = FacesContext.getCurrentInstance().getApplication().getNavigationHandler();
		nh.handleNavigation(FacesContext.getCurrentInstance(), null, "/user/listaEmails.xhtml?faces-redirect=true");
	}

	public void enviarEmail() {
		email.setRemetente(usuario);
		email.setDestinatario(usuarioService.findByLogin(destinatario));
		emailService.enviarEmail(email);
		FacesContext faces = FacesContext.getCurrentInstance();
		faces.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
				"Email", "Email enviado com sucesso!"));
		email = new Email();
	}

	public void detalhaEmail(){
		NavigationHandler nh = FacesContext.getCurrentInstance().getApplication().getNavigationHandler();
		nh.handleNavigation(FacesContext.getCurrentInstance(), null, "/user/detalhaEmail.xhtml?faces-redirect=true");
	}

	public String salvarRascunho(){
		email.setRemetente(usuario);
		email.setDestinatario(null);
		email.setFiltro(new Filtro(EnumFiltro.RASCUNHOS.getValor(),EnumFiltro.RASCUNHOS.getDescricao()));
		emailService.salvaRascunho(email);
		FacesContext faces = FacesContext.getCurrentInstance();
		faces.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Email", "Rascunho salvo com sucesso!"));
		email = new Email();
		return "";
	}
	
	public String obtemConfiguracoes(){
		return "";
	}

	public void excluiEmails(){
		if(EnumFiltro.obtemFiltroPorId(filtroSelecionado.getId()) != EnumFiltro.LIXO){
			emailService.moveEmailsParaLixeira(emailsSelecionados);
		}else{
			emailService.excluiEmails(emailsSelecionados);
		}
		obtemEmailsCaixaDeEntrada(); 
		emailsSelecionados = null;
		FacesContext faces = FacesContext.getCurrentInstance();
		faces.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Email", "Emails excluídos com sucesso!"));
	}
	
	private void configuraFiltroSelecionado(EnumFiltro filtro){
		this.filtroSelecionado.setId(filtro.getValor());
		this.filtroSelecionado.setNome(filtro.getDescricao());
	}
	
	public void editarEmail(){
		NavigationHandler nh = FacesContext.getCurrentInstance().getApplication().getNavigationHandler();
		nh.handleNavigation(FacesContext.getCurrentInstance(), null, "/user/novoEmail.xhtml?faces-redirect=true");
	}
	
	public List<Email> getEmails() {
		return emails;
	}

	public void setEmails(List<Email> emails) {
		this.emails = emails;
	}

	public MenuModel getSimpleMenuModel() {
		return simpleMenuModel;
	}

	public void setSimpleMenuModel(MenuModel simpleMenuModel) {
		this.simpleMenuModel = simpleMenuModel;
	}
	
	public void setDestinatario(String destinatario) {
		this.destinatario = destinatario;
	}
	
	public String getDestinatario() {
		return destinatario;
	}

	public Email getEmail() {
		return email;
	}

	public void setEmail(Email email) {
		this.email = email;
	}

	public Email getEmailSelecionado() {
		return emailSelecionado;
	}

	public void setEmailSelecionado(Email emailSelecionado) {
		this.emailSelecionado = emailSelecionado;
	}

	public List<Email> getEmailsSelecionados() {
		return emailsSelecionados;
	}

	public void setEmailsSelecionados(List<Email> emailsSelecionados) {
		this.emailsSelecionados = emailsSelecionados;
	}

	public Filtro getFiltroSelecionado() {
		return filtroSelecionado;
	}

	public void setFiltroSelecionado(Filtro filtroSelecionado) {
		this.filtroSelecionado = filtroSelecionado;
	}
	
}
