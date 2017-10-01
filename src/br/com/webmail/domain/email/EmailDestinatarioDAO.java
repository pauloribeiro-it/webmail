package br.com.webmail.domain.email;

import br.com.webmail.dao.DAO;

public class EmailDestinatarioDAO extends DAO<EmailDestinatario, Email>{

	public EmailDestinatarioDAO(Class<EmailDestinatario> especializacao) {
		super(especializacao);
	}

}
