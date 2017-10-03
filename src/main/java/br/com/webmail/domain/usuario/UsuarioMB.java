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

	private String senha;

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

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String cadastro() {
		usuarioBean.registraUsuario(usuario, senha);
		FacesContext faces = FacesContext.getCurrentInstance();
		faces.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
				null, "Cadastro concluído com sucesso."));
		return "login.jsf";
	}

}
