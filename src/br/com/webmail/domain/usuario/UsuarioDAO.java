package br.com.webmail.domain.usuario;

import javax.persistence.Query;

import br.com.webmail.dao.GenericDAO;

public class UsuarioDAO extends GenericDAO<Usuario, Long> {

	public Usuario findByLogin(String login) {
		Query query = entityManager.createQuery(getQueryByFullName("Usuario.findUsuarioByLogin"))
				.setParameter("login", login);
		return (Usuario) query.getSingleResult();
	}
}
