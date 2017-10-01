package br.com.webmail.domain.email;

import java.util.List;

import br.com.webmail.dao.DAO;

public class EmailDAO extends DAO<Email, Long> {

	public EmailDAO(Class<Email> especializacao) {
		super(especializacao);
	}

	@SuppressWarnings("unchecked")
	public List<Email> obtemEmailsFromFiltros(List<EmailFiltro> filtros) {
		List<Email> emails = null;
		emails = (List<Email>) entityManager.createQuery(
				getQueryByFullName("Email.emailFromFiltro")).setParameter(1,
				filtros);
		return emails;
	}
	
}