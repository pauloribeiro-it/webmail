package br.com.webmail.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.crypto.hash.Sha256Hash;

public class WebmailUtil {
	
	private static final String DOMINIOEMAIL = "@webmail.com.br";
	
	public static List<String> getEmails(String texto) {
		List<String> emails = new ArrayList<String>();
		if(texto != null && !texto.isEmpty())
			emails.addAll(Arrays.asList(texto.split(";")));
		return emails;
	}
	
	public static String getNomeUsuarioLogado(){
		return SecurityUtils.getSubject().getPrincipal().toString();
	}
	
	public static String getEncryptedPassword(String password){
		return new Sha256Hash(password).toHex();
	}
	
	public static String getEmailFormatado(String login) {
		return login + WebmailUtil.DOMINIOEMAIL;
	}

}
