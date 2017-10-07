package br.com.webmail.exception;

public class AutenticacaoException extends RuntimeException{

	private static final long serialVersionUID = 2145751410969660826L;

	public AutenticacaoException(String msg){
		super(msg);
	}
}
