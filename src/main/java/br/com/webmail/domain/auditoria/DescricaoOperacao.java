package br.com.webmail.domain.auditoria;

import java.util.Map;

public class DescricaoOperacao {
	private String nomeMetodo;
	private Map<String,Object> parametros;
	
	public String getNomeMetodo() {
		return nomeMetodo;
	}
	public void setNomeMetodo(String nomeMetodo) {
		this.nomeMetodo = nomeMetodo;
	}
	public Map<String, Object> getParametros() {
		return parametros;
	}
	public void setParametros(Map<String, Object> parametros) {
		this.parametros = parametros;
	}
	
}
