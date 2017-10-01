package br.com.webmail.domain.usuario;

import javax.persistence.Query;

import br.com.webmail.dao.DAO;

public class UsuarioDAO extends DAO<Usuario, Long> {

	public UsuarioDAO(Class<Usuario> especializacao) {
		super(especializacao);
	}

	public Usuario findByLogin(String login) {
		Query query = entityManager.createQuery(getQueryByFullName("Usuario.findUsuarioByLogin"))
				.setParameter("login", login);
		return (Usuario) query.getSingleResult();
	}
}
