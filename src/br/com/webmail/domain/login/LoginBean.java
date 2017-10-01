package br.com.webmail.domain.login;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

import br.com.webmail.dao.DAO;
import br.com.webmail.dao.DaoProduces;
import br.com.webmail.domain.autorizacao.AutorizacaoIFC;

@Stateless
public class LoginBean implements LoginIFC{
	@PersistenceContext(unitName = "webmail", type = PersistenceContextType.TRANSACTION)
	protected EntityManager entityManager;
	
	@Inject
	@DaoProduces
	private DAO<Login, String> dao;
	
	@EJB
	private AutorizacaoIFC autorizacaoBean;

	public void save(Login login) {
//		dao.save(login);
		autorizacaoBean.save(login);
	}

	public Login find(String login) {
//		return dao.find(login);
		entityManager.createQuery("select u from Usuario u").getResultList().forEach(lo->System.out.println(lo));
		return null;
	}

	public void delete(Login login) {
		autorizacaoBean.delete(login);
//		dao.delete(login);
	}
}
