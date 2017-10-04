package br.com.webmail.domain.email;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

import org.jboss.logging.Logger;
import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.DefaultSubMenu;
import org.primefaces.model.menu.MenuModel;

import br.com.webmail.domain.filtro.Filtro;
import br.com.webmail.domain.filtro.FiltroService;
import br.com.webmail.domain.usuario.Usuario;
import br.com.webmail.domain.usuario.UsuarioDAO;
import br.com.webmail.util.WebmailUtil;

@ViewScoped
@ManagedBean(name = "emailManagedBean")
public class EmailMB implements Serializable {
	private static final long serialVersionUID = 5052247699927310089L;
	private MenuModel simpleMenuModel = new DefaultMenuModel();
	private DefaultSubMenu submenu;
	private List<Email> emails;
	
	@Inject
	private Email email;

	@EJB
	private FiltroService filtroService;

	@EJB
	private EmailService emailService;

//	@Inject
//	private UsuarioDAO usuarioDAO;

	private String emailsTexto;

	private String emailsTextoCC;

	private String emailsTextoCCO;

	private static final Logger logger = Logger.getLogger(EmailMB.class);
	
	public EmailMB() {

	}

	@PostConstruct
	public void configuraPagina() {
		this.submenu = new DefaultSubMenu("Emails");
		simpleMenuModel.addElement(submenu);
		configuraFiltrosPersonalizados();
		obtemEmailsCaixaDeEntrada();
	}

	private void obtemEmailsCaixaDeEntrada() {

	}

	private void configuraFiltrosPersonalizados() {
		String userName = WebmailUtil.getNomeUsuarioLogado();
//		List<Filtro> filtrosResult = filtroService.obtemFiltrosUsuario(usuarioDAO
//				.findByLogin(userName));
//		for (Filtro filtro : filtrosResult) {
//			DefaultMenuItem itemMenu = new DefaultMenuItem(filtro.getNome());
//			itemMenu.setCommand("#{emailMB.retornaFiltros}");
//			submenu.addElement(itemMenu);
//		}
	}

	public void retornaFiltros(ActionEvent e) {

	}

	public String enviarEmail() {
		configuraEmail();
		saveEmail();
		return "/user/index.jsf";
	}

	private void configuraEmail() {
		configuraDatas();
//		Usuario remetente = usuarioDAO.findByLogin(WebmailUtil
//				.getNomeUsuarioLogado());
//		email.setRemetente(remetente);
//		email.setDestinatarios(getDestinatarios());
	}

	private void saveEmail() {
		email.setDestinatarios(null);
		emailService.save(email);
		logger.info("inseriu email");
		emailService.saveDestinatarios(getDestinatarios());
		logger.info("inseriu destinatarios");
		emailService.saveEmailFiltro(email,
				filtroService.obtemFiltrosUsuario(email.getRemetente()));
		logger.info("inseriu email_filtro");
	}

	private List<EmailDestinatario> getDestinatarios() {
		List<EmailDestinatario> destinatarios = new ArrayList<EmailDestinatario>();
		List<String> destinatariosTexto = WebmailUtil.getEmails(emailsTexto);
		List<String> destinatariosCC = WebmailUtil.getEmails(emailsTextoCC);
		List<String> destinatariosCCO = WebmailUtil.getEmails(emailsTextoCCO);
		destinatarios.addAll(constroiEmailDestinatarios(email,
				destinatariosTexto, false, false));
		destinatarios.addAll(constroiEmailDestinatarios(email, destinatariosCC,
				true, false));
		destinatarios.addAll(constroiEmailDestinatarios(email,
				destinatariosCCO, false, true));
		return destinatarios;
	}

	private List<EmailDestinatario> constroiEmailDestinatarios(Email email,
			List<String> destinatarios, boolean isCC, boolean isCCO) {
		List<EmailDestinatario> emailsDestinatario = new ArrayList<EmailDestinatario>();
		if (destinatarios.size() > 0)
			for (String str : destinatarios)
				emailsDestinatario.add(constroiEmailDestinatario(email, str,
						isCC, isCCO));
		return emailsDestinatario;
	}

	private EmailDestinatario constroiEmailDestinatario(Email email,
			String destinatario, boolean isCC, boolean isCCO) {
		EmailDestinatario emailDestinatario = new EmailDestinatario();
//		emailDestinatario.setEmail(email);
//		emailDestinatario.setCC(isCC);
//		emailDestinatario.setCCO(isCCO);
//		emailDestinatario.setUsuario(usuarioDAO.findByLogin(destinatario));
		return emailDestinatario;
	}

	private void configuraDatas() {
		email.setDataHoraCriacao(new Date());
		email.setDataHoraDeletado(null);
		email.setDataHoraEnviado(new Date());
		email.setDataHoraExcluido(null);
		email.setDataHoraLido(null);
		email.setDataHoraRecebido(null);
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
