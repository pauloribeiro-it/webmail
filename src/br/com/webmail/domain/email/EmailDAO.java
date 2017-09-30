package br.com.webmail.domain.email;

import java.util.List;

import br.com.webmail.dao.GenericDAO;

public class EmailDAO extends GenericDAO<Email, Long> {

	@SuppressWarnings("unchecked")
	public List<Email> obtemEmailsFromFiltros(List<EmailFiltro> filtros) {
		List<Email> emails = null;
		emails = (List<Email>) entityManager.createQuery(
				getQueryByFullName("Email.emailFromFiltro")).setParameter(1,
				filtros);
		return emails;
	}
	
}