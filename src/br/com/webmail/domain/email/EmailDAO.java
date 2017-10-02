package br.com.webmail.domain.email;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Named;

import br.com.webmail.dao.DAO;

@Named("emailDao")
@Stateless
public class EmailDAO extends DAO<Email, Long> {
	
	public EmailDAO(){
		
	}
	
//	public EmailDAO(Class<Email> especializacao,EntityManager em) {
//		super(especializacao,em);
//	}

	@SuppressWarnings("unchecked")
	public List<Email> obtemEmailsFromFiltros(List<EmailFiltro> filtros) {
		List<Email> emails = null;
		emails = (List<Email>) entityManager.createQuery(
				getQueryByFullName("Email.emailFromFiltro")).setParameter(1,
				filtros);
		return emails;
	}
	
}