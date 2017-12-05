package br.com.webmail.domain.apoio;

public class SenhaInvalidaException extends Exception{

	private static final long serialVersionUID = 2357692831122196717L;
	
	public SenhaInvalidaException(String msg){
		super(msg);
	}
}
