package br.com.webmail.interceptors;

import javax.ejb.EJB;
import javax.inject.Named;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

import br.com.webmail.domain.auditoria.AuditoriaLoginService;
import br.com.webmail.domain.usuario.Usuario;
import br.com.webmail.util.WebmailUtil;

@Interceptor
@AuditLogin
@Named
public class AuditoriaLoginInterceptor {
	@EJB
	private AuditoriaLoginService auditoriaLoginService;
	
	@AroundInvoke
	public Object auditaLogin(InvocationContext invocationContext) throws Exception{
		Object obj = null;
		
		obj = invocationContext.proceed();
		Usuario usuario = WebmailUtil.getUsuarioSessao();
		if(usuario != null){
			auditoriaLoginService.auditaLogin(usuario);
		}
		return obj;
	}
}
