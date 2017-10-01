package br.com.webmail.domain.login;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.application.NavigationHandler;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.apache.shiro.subject.Subject;

@Named(value="loginMB")
@RequestScoped
public class LoginMB {
	private String username;
	private String password;
	
	@EJB
	private LoginIFC loginBean;
	
	public LoginMB(){
		
	}
	@PostConstruct
	public void init(){
		System.out.println("inicio");
	}
	
	public void loginUser() {

		try {

			Subject currentUser = SecurityUtils.getSubject();
			UsernamePasswordToken token = new UsernamePasswordToken(username, new Sha256Hash(password).toHex());

			currentUser.login(token);

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

	public void authorizedUserControl() {

		if (null != SecurityUtils.getSubject().getPrincipal()) {

			NavigationHandler nh = FacesContext.getCurrentInstance().getApplication().getNavigationHandler();
			nh.handleNavigation(FacesContext.getCurrentInstance(), null, "/member/index.xhtml?faces-redirect=true");

		}
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
	
	public void testa(){
		loginBean.find("");
	}
}
