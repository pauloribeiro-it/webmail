package br.com.webmail.domain.email;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Named;

import br.com.webmail.dao.CrudDao;

@Stateless
public class EmailDAO extends CrudDao<Email, Long> {
	
	public EmailDAO(){
		
	}
	
	@SuppressWarnings("unchecked")
	public List<Email> obtemEmailsFromFiltros(List<EmailFiltro> filtros) {
		List<Email> emails = null;
		emails = (List<Email>) getEntityManager().createQuery(
				getQueryByFullName("Email.emailFromFiltro")).setParameter(1,
				filtros);
		return emails;
	}
	
}