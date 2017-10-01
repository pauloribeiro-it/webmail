package br.com.webmail.domain.login;

import br.com.webmail.dao.DAO;

public class LoginDAO extends DAO<Login, String>{

	public LoginDAO(Class<Login> especializacao) {
		super(especializacao);
	}

}
