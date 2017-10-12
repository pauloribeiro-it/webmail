package br.com.webmail.domain.auditoria;

import java.util.Map;

public class DescricaoOperacao {
	private String nome;
	private Map<String,Object> parametros;
	
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Map<String, Object> getParametros() {
		return parametros;
	}
	public void setParametros(Map<String, Object> parametros) {
		this.parametros = parametros;
	}
	
}
