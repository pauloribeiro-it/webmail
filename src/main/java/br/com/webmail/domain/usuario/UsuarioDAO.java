package br.com.webmail.domain.usuario;

import javax.ejb.Stateless;
import javax.persistence.Query;

import br.com.webmail.dao.CrudDao;
import br.com.webmail.dao.EnumQueries;

@Stateless
public class UsuarioDAO extends CrudDao<Usuario, Long> {

	private static final long serialVersionUID = 6860486555581144569L;

	public Usuario findByLogin(String login) {
		Query query = getEntityManager().createQuery(EnumQueries.FINDUSUARIOBYLOGIN.getQuery())
				.setParameter("login", login);
		return (Usuario) query.getSingleResult();
	}
}
