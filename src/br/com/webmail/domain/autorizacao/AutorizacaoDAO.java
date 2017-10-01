package br.com.webmail.domain.autorizacao;

import br.com.webmail.dao.DAO;
import br.com.webmail.domain.login.Login;

public class AutorizacaoDAO extends DAO<Autorizacao, Login>{

	public AutorizacaoDAO(Class<Autorizacao> especializacao) {
		super(especializacao);
	}

}
