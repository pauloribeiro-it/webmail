package br.com.webmail.domain.login;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

import br.com.webmail.dao.DAO;
import br.com.webmail.dao.DaoProduces;
import br.com.webmail.daotest.CrudDao;
import br.com.webmail.daotest.Dao;
import br.com.webmail.daotest.LoginDao;
import br.com.webmail.domain.autorizacao.AutorizacaoService;
import br.com.webmail.domain.filtro.Filtro;

@Stateless
public class LoginEJB implements LoginService{
	@PersistenceContext(unitName = "webmail", type = PersistenceContextType.TRANSACTION)
	protected EntityManager entityManager;
	
	@Inject
	@DaoProduces
	private DAO<Login, String> dao;
	
//	@Inject
//	@DaoProduces
//	private EmailDAO emailDao;
	
	@Inject
	private LoginDao loginDao;
	
	@Inject @Dao
	private CrudDao<Filtro,Long> filtroDao;
	
	@EJB
	private AutorizacaoService autorizacaoBean;

	public void save(Login login) {
//		dao.save(login);
		autorizacaoBean.save(login);
	}

	public Login find(String login) {
//		dao.find(login);
//		entityManager.createQuery("select u from Usuario u").getResultList().forEach(lo->System.out.println(lo));
		System.out.println(loginDao.findAll());
		return null;
	}

	public void delete(Login login) {
		autorizacaoBean.delete(login);
//		dao.delete(login);
	}
}
