package br.com.webmail.domain.usuario;

import javax.ejb.Stateless;
import javax.persistence.Query;

import br.com.webmail.dao.CrudDao;

@Stateless
public class UsuarioDAO extends CrudDao<Usuario, Long> {

	public Usuario findByLogin(String login) {
		Query query = getEntityManager().createQuery(getQueryByFullName("Usuario.findUsuarioByLogin"))
				.setParameter("login", login);
		return (Usuario) query.getSingleResult();
	}
}
