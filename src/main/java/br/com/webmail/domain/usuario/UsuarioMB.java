package br.com.webmail.domain.usuario;

import java.io.Serializable;
import java.util.Date;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

@ManagedBean(name = "usuarioMB")
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
		configuraDatas();
		usuarioBean.save(usuario, senha);
		FacesContext faces = FacesContext.getCurrentInstance();
		faces.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
				null, "Cadastro concluído com sucesso."));
		return "login.jsf";
	}

	private void configuraDatas() {
		usuario.setDataCriacao(new Date());
		usuario.setUltimoLogin(new Date());
	}

}
