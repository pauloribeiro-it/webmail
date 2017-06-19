package br.com.webmail.dao;

import java.util.List;

import br.com.webmail.entities.Email;
import br.com.webmail.entities.EmailFiltro;

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