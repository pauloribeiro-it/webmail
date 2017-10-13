package br.com.webmail.interceptors;

import java.lang.reflect.Parameter;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.ejb.EJB;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.webmail.domain.auditoria.AuditoriaOperacao;
import br.com.webmail.domain.auditoria.AuditoriaOperacaoService;
import br.com.webmail.domain.auditoria.DescricaoOperacao;
import br.com.webmail.util.WebmailUtil;

@Interceptor
@AuditOperacao
public class AuditoriaOperacaoInterceptor {

	@EJB
	private AuditoriaOperacaoService auditoriaService;
	
	@AroundInvoke
	public Object auditaOperacao(InvocationContext invocationContext) throws Exception {
		Object obj = null;
		String descErro = "";
		try {
			obj = invocationContext.proceed();
		} catch (Exception e) {
			descErro = e.getMessage();
		} finally{
			AuditoriaOperacao auditoria = criaAuditoriaOperacao(descErro, invocationContext);
			auditoriaService.realizaAuditoriaOperacao(auditoria);
		}
		
		return obj;
	}
	
	private AuditoriaOperacao criaAuditoriaOperacao(String descErro,InvocationContext invocationContext) throws Exception{
		AuditoriaOperacao auditoria = new AuditoriaOperacao();
		auditoria.setAuditoriaLogin(WebmailUtil.getAuditoriaLogin());
		auditoria.setDescErro(descErro);
		auditoria.setDataOperacao(new Date());
		
		DescricaoOperacao descOperacao = obtemDescricaoOperacao(invocationContext);
		auditoria.setDescOperacao(converteDescricaoOperacaoParaJSON(descOperacao));
		
		return auditoria;
	}
	
	private DescricaoOperacao obtemDescricaoOperacao(InvocationContext context) throws Exception{
		DescricaoOperacao descOperacao = new DescricaoOperacao();
		Map<String,Object> parametros = new LinkedHashMap<>();
		descOperacao.setNomeMetodo(context.getMethod().toString());
		descOperacao.setParametros(parametros);
		
		for(int i = 0;i<context.getMethod().getParameterCount();i++){
			Parameter param = context.getMethod().getParameters()[i];
			parametros.put(param.getName(), param.getType().getMethod("toString").invoke(context.getParameters()[i]));
		}
		
		return descOperacao;
	}
	
	private String converteDescricaoOperacaoParaJSON(DescricaoOperacao descOperacao) {
		try {
			return new ObjectMapper().writeValueAsString(descOperacao);
		} catch (JsonProcessingException e) {
			return e.getMessage();
		}
	}
}
