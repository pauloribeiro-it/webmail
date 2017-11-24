package br.com.webmail.interceptors;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

import org.apache.shiro.SecurityUtils;

import br.com.webmail.domain.auditoria.AuditoriaLoginService;
import br.com.webmail.domain.usuario.Usuario;
import br.com.webmail.util.WebmailUtil;

@Interceptor
@AuditLogin
public class AuditoriaLoginInterceptor implements Serializable{
	private static final long serialVersionUID = 8759036332492812398L;
	@EJB
	private AuditoriaLoginService auditoriaLoginService;
	
	@AroundInvoke
	public Object auditaLogin(InvocationContext invocationContext) throws Exception{
		Object obj = null;
		
		obj = invocationContext.proceed();
		Usuario usuario = WebmailUtil.getUsuarioSessao();
		if(usuario != null){
			auditoriaLoginService.auditaLogin(usuario);
			SecurityUtils.getSubject().getSession().setAttribute(WebmailUtil.AUDITORIALOGIN, auditoriaLoginService.obtemAuditoriaUsuario(usuario));
		}
		return obj;
	}
}
