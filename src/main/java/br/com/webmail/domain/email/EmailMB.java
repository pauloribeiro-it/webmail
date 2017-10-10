package br.com.webmail.domain.email;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;
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
import br.com.webmail.util.WebmailUtil;

@ViewScoped
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
	
	private String emailsTexto;

	private String emailsTextoCC;

	private String emailsTextoCCO;

	public EmailMB() {

	}

	@PostConstruct
	public void configuraPagina() {
		email = new Email();
		usuario = WebmailUtil.getUsuarioSessao();
		this.submenu = new DefaultSubMenu("Emails");
		simpleMenuModel.addElement(submenu);
		configuraFiltrosPersonalizados();
		obtemEmailsCaixaDeEntrada();
	}

	private void obtemEmailsCaixaDeEntrada() {

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
		emailService.obtemEmailsPorUsuarioEFiltro(usuario, idFiltro.intValue());
	}

	public String enviarEmail() {
		email.setRemetente(usuario);
		email.setDestinatarios(getDestinatarios());
		emailService.enviarEmail(email);
		return "/user/index.jsf";
	}

	private List<EmailDestinatario> getDestinatarios() {
		List<EmailDestinatario> destinatarios = new ArrayList<EmailDestinatario>();
		List<String> destinatariosTexto = WebmailUtil.getEmails(emailsTexto);
		List<String> destinatariosCC = WebmailUtil.getEmails(emailsTextoCC);
		List<String> destinatariosCCO = WebmailUtil.getEmails(emailsTextoCCO);
		destinatarios.addAll(constroiEmailDestinatarios(email, destinatariosTexto, false, false));
		destinatarios.addAll(constroiEmailDestinatarios(email, destinatariosCC,	true, false));
		destinatarios.addAll(constroiEmailDestinatarios(email, destinatariosCCO, false, true));
		return destinatarios;
	}

	private List<EmailDestinatario> constroiEmailDestinatarios(Email email,	List<String> destinatarios, boolean isCC, boolean isCCO) {
		List<EmailDestinatario> emailsDestinatario = new ArrayList<EmailDestinatario>();
		if (destinatarios.size() > 0){
			for (String str : destinatarios){
				emailsDestinatario.add(constroiEmailDestinatario(email, str,isCC, isCCO));
			}
		}
		return emailsDestinatario;
	}

	private EmailDestinatario constroiEmailDestinatario(Email email,String destinatario, boolean isCC, boolean isCCO) {
		EmailDestinatario emailDestinatario = new EmailDestinatario();
		emailDestinatario.setEmail(email);
		emailDestinatario.setCC(isCC);
		emailDestinatario.setCCO(isCCO);
		emailDestinatario.setUsuario(usuario);
		return emailDestinatario;
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

	public Email getEmail() {
		return email;
	}

	public void setEmail(Email email) {
		this.email = email;
	}

	public String getEmailsTexto() {
		return emailsTexto;
	}

	public void setEmailsTexto(String emailsTexto) {
		this.emailsTexto = emailsTexto;
	}

	public String getEmailsTextoCC() {
		return emailsTextoCC;
	}

	public void setEmailsTextoCC(String emailsTextoCC) {
		this.emailsTextoCC = emailsTextoCC;
	}

	public String getEmailsTextoCCO() {
		return emailsTextoCCO;
	}

	public void setEmailsTextoCCO(String emailsTextoCCO) {
		this.emailsTextoCCO = emailsTextoCCO;
	}

}
