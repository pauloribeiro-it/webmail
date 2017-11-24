package br.com.webmail.domain.login;

import java.io.Serializable;
import java.util.Base64;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.application.NavigationHandler;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.apache.shiro.subject.Subject;

import br.com.webmail.domain.auditoria.AuditoriaLoginService;
import br.com.webmail.domain.usuario.Usuario;
import br.com.webmail.domain.usuario.UsuarioService;
import br.com.webmail.interceptors.AuditLogin;
import br.com.webmail.util.WebmailUtil;

@Named(value="loginMB")
@SessionScoped
public class LoginMB implements Serializable{
	private static final long serialVersionUID = 1565428517636255897L;
	private String username;
	private String password;
	
	@EJB
	private UsuarioService usuarioService;
	
	@EJB
	private AuditoriaLoginService auditoriaLoginService;
	
	public LoginMB(){
		
	}

	@AuditLogin
	public void loginUser() {

		try {

			Subject currentUser = SecurityUtils.getSubject();
			UsernamePasswordToken token = new UsernamePasswordToken(username, new Sha256Hash(password).toHex());
			currentUser.getSession(true);
			currentUser.login(token);
			if (null != SecurityUtils.getSubject().getPrincipal()) {
				//configura o id da sessão
				Usuario usuarioLogado = usuarioService.findByLogin(username);
				usuarioLogado.setIdSessao(getSessionId(usuarioLogado));
				
				//configura na sessão
				currentUser.getSession().setAttribute(WebmailUtil.USER, usuarioLogado);
				
				NavigationHandler nh = FacesContext.getCurrentInstance().getApplication().getNavigationHandler();
				nh.handleNavigation(FacesContext.getCurrentInstance(), null, "/user/listaEmails.xhtml?faces-redirect=true");
			}
		} catch (UnknownAccountException uae) {
			uae.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Login failed!", "Your username wrong"));

		} catch (IncorrectCredentialsException ice) {
			ice.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Login failed!", "Password is incorrect"));

		} catch (LockedAccountException lae) {
			lae.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Login failed!", "This username is locked"));

		} catch (AuthenticationException aex) {
			aex.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Login failed!", aex.toString()));
		}
	}
	
	public void logout(){
		Subject subject = SecurityUtils.getSubject();
		subject.getSession().setAttribute(WebmailUtil.USER, null);
		subject.getSession().setAttribute(WebmailUtil.AUDITORIALOGIN, null);
		SecurityUtils.getSubject().logout();
		NavigationHandler nh = FacesContext.getCurrentInstance().getApplication().getNavigationHandler();
		nh.handleNavigation(FacesContext.getCurrentInstance(), null, "listaEmails.xhtml?faces-redirect=true");
		((HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest()).getSession().invalidate();
	}
	

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	private String getSessionId(Usuario usuario){
		String seed = usuario.getEmail()+System.currentTimeMillis();
		return Base64.getEncoder().encodeToString(seed.getBytes());
	}
}
