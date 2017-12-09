package br.com.webmail.domain.usuario;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

@Named("usuarioMB")
@RequestScoped
public class UsuarioMB implements Serializable {

	private static final long serialVersionUID = -7196103259003542702L;

	@Inject
	private Usuario usuario;

	private String senhaNova;

	private String confirmacaoSenhaNova;
	@EJB
	private UsuarioService usuarioBean;

	public UsuarioMB() {
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getSenhaNova() {
		return senhaNova;
	}

	public void setSenhaNova(String senha) {
		this.senhaNova = senha;
	}

	public String getConfirmacaoSenhaNova() {
		return confirmacaoSenhaNova;
	}

	public void setConfirmacaoSenhaNova(String confirmacaoSenhaNova) {
		this.confirmacaoSenhaNova = confirmacaoSenhaNova;
	}
	public void cadastro() {
		FacesContext faces = FacesContext.getCurrentInstance();
		usuarioBean.registraUsuario(usuario, senhaNova);
		faces.addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO, 
				"Cadastro concluído com sucesso.", 
				"Cadastro"));
		usuario = new Usuario();
	}

}
