package br.com.webmail.managedbean;

import javax.faces.application.FacesMessage;
import javax.faces.application.NavigationHandler;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.apache.shiro.subject.Subject;

@ManagedBean(name = "loginMB")
@SessionScoped
public class LoginMB {
	private String username;
	private String password;

	public LoginMB(){
		
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
}
