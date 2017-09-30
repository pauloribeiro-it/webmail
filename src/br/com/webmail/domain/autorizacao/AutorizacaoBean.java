package br.com.webmail.domain.autorizacao;

import javax.ejb.Stateless;
import javax.inject.Inject;

import br.com.webmail.dao.GenericDAO;
import br.com.webmail.domain.login.Login;

@Stateless
public class AutorizacaoBean implements AutorizacaoIFC{
	@Inject
	private GenericDAO<Autorizacao, Login> dao;

	public void save(Login login) {
		Autorizacao autorizacao = new Autorizacao();
		autorizacao.setLogin(login);
		autorizacao.setPapel("ROLE_USUARIO");
		dao.save(autorizacao);
	}

	public Autorizacao find(Login login) {
		return dao.find(login);
	}

	public void delete(Login login) {
		dao.delete(find(login));
	}
}
