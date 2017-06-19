package br.com.webmail.dao;

import javax.persistence.Query;

import br.com.webmail.entities.Usuario;

public class UsuarioDAO extends GenericDAO<Usuario, Long> {

	public Usuario findByLogin(String login) {
		Query query = entityManager.createQuery(getQueryByFullName("Usuario.findUsuarioByLogin"))
				.setParameter("login", login);
		return (Usuario) query.getSingleResult();
	}
}
