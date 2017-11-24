package br.com.webmail.domain.apoio;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import br.com.webmail.domain.login.Login;
import br.com.webmail.domain.login.LoginService;
import br.com.webmail.domain.usuario.Usuario;
import br.com.webmail.util.WebmailUtil;

@RequestScoped
@Named("apoioMB")
public class ApoioMB implements Serializable{

	private static final long serialVersionUID = -3020681636886625551L;
	
	@EJB
	private LoginService loginService;
	private Usuario usuarioLogado;
	private Login login;
	private String senhaNova;
	
	public ApoioMB(){
		
	}
	
	public void alterarSenha(){
		this.login.setSenha(senhaNova);
		loginService.atualizaSenhaLogin(login);
	}
	
	@PostConstruct
	private void obtemUsuarioLogado(){
		this.usuarioLogado = WebmailUtil.getUsuarioSessao();
		this.login = loginService.obtemLoginPorUsuario(usuarioLogado);
		FacesContext faces = FacesContext.getCurrentInstance();
		faces.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Senha", "Senha alterada com sucesso!"));
	}
	
	public Usuario getUsuarioLogado() {
		return usuarioLogado;
	}
	
	public void setUsuarioLogado(Usuario usuarioLogado) {
		this.usuarioLogado = usuarioLogado;
	}

	public String getSenhaNova() {
		return senhaNova;
	}

	public void setSenhaNova(String senhaNova) {
		this.senhaNova = senhaNova;
	}
	
}
