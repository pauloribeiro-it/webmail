package br.com.webmail.domain.filtro;

import br.com.webmail.dao.DAO;
import br.com.webmail.domain.email.EmailFiltro;

public class EmailFiltroDAO extends DAO<EmailFiltro, Long>{

	public EmailFiltroDAO(Class<EmailFiltro> especializacao) {
		super(especializacao);
	}

}
