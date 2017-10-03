package br.com.webmail.domain.login;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

import br.com.webmail.dao.CrudDao;
import br.com.webmail.dao.Dao;
import br.com.webmail.domain.autorizacao.AutorizacaoService;
import br.com.webmail.domain.filtro.Filtro;
import br.com.webmail.domain.usuario.Usuario;
import br.com.webmail.util.WebmailUtil;

@Stateless
public class LoginEJB implements LoginService{
	@PersistenceContext(unitName = "webmail", type = PersistenceContextType.TRANSACTION)
	protected EntityManager entityManager;
	
	@Inject
	@Dao
	private CrudDao<Login, String> dao;
	
	@Inject @Dao
	private CrudDao<Filtro,Long> filtroDao;
	
	@EJB
	private AutorizacaoService autorizacaoBean;

	public void save(Login login) {
		autorizacaoBean.save(login);
	}

	public Login configuraPerfil(Usuario usuario, String senha) {
		Login login = new Login();
		login.setUsuario(usuario);
		login.setAtivo(true);
		login.setSenha(senha);
		login.setLogin(WebmailUtil.getEmailFormatado(usuario.getEmail()));
		return login;
	}
	
	
}
