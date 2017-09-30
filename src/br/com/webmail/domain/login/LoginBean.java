package br.com.webmail.domain.login;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;

import br.com.webmail.dao.DAOInterface;
import br.com.webmail.domain.autorizacao.AutorizacaoIFC;

@Stateless
public class LoginBean implements LoginIFC{

	@Inject
	private DAOInterface<Login, String> dao;

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
