package br.com.webmail.sessionbeans;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;

import br.com.web.sessionsbean.ifc.AutorizacaoIFC;
import br.com.web.sessionsbean.ifc.LoginIFC;
import br.com.webmail.dao.GenericDAO;
import br.com.webmail.entities.Login;

@Stateless
public class LoginBean implements LoginIFC{

	@Inject
	private GenericDAO<Login, String> dao;

	@EJB
	private AutorizacaoIFC autorizacaoBean;

	public void save(Login login) {
		dao.save(login);
		autorizacaoBean.save(login);
	}

	public Login find(String login) {
		return dao.find(login);
	}

	public void delete(Login login) {
		autorizacaoBean.delete(login);
		dao.delete(login);
	}
}
