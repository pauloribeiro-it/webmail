package br.com.webmail.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.security.core.context.SecurityContextHolder;

public class WebmailUtil {
	
	public static List<String> getEmails(String texto) {
		List<String> emails = new ArrayList<String>();
		if(texto != null && !texto.isEmpty())
			emails.addAll(Arrays.asList(texto.split(";")));
		return emails;
	}

	public static String getUsuarioLogado() {
		return  SecurityContextHolder.getContext()
				.getAuthentication().getName();
	}
}
