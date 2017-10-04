package br.com.webmail.domain.autorizacao;

import javax.ejb.Stateless;
import javax.inject.Inject;

import br.com.webmail.dao.CrudDao;
import br.com.webmail.dao.Dao;
import br.com.webmail.domain.login.Login;

@Stateless
public class AutorizacaoServiceImpl implements AutorizacaoService{
	@Inject @Dao
	private CrudDao<Autorizacao, Login> dao;

	public void save(Login login) {
		Autorizacao autorizacao = new Autorizacao();
		autorizacao.setLogin(login);
		autorizacao.setPapel("admin");
		dao.insert(autorizacao);
	}

	public Autorizacao find(Login login) {
		return dao.find(login);
	}

	public void delete(Login login) {
		dao.delete(login);
	}
}
