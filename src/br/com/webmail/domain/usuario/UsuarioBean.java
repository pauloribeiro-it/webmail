package br.com.webmail.domain.usuario;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;

import br.com.webmail.dao.GenericDAO;
import br.com.webmail.domain.filtro.Filtro;
import br.com.webmail.domain.filtro.FiltroIFC;
import br.com.webmail.domain.login.Login;
import br.com.webmail.domain.login.LoginIFC;

@Stateless
public class UsuarioBean implements UsuarioIFC {

	@Inject
	private GenericDAO<Usuario, Long> dao;

	@EJB
	private LoginIFC loginBean;

	@EJB
	private FiltroIFC filtroBean;

	public Usuario find(Long id) {
		return dao.find(id);
	}

	public void save(Usuario usuario, String senha) {
		Login login = configuraPerfil(usuario, senha);
		usuario.setEmail(getEmailFormatado(usuario.getEmail()));
		dao.save(usuario);
		loginBean.save(login);
		associaFiltrosPadrao(usuario, filtroBean.obtemFiltrosPadrao());
	}

	public void merge(Usuario usuario) {
		dao.merge(usuario);
	}

	public void delete(Usuario usuario) {
		dao.delete(usuario);
	}

	public List<Usuario> findAll() {
		return dao.findAll();
	}

	private Login configuraPerfil(Usuario usuario, String senha) {
		Login login = new Login();
		login.setUsuario(usuario);
		login.setAtivo(true);
		login.setSenha(senha);
		login.setLogin(getEmailFormatado(usuario.getEmail()));
		return login;
	}

	private String getEmailFormatado(String login) {
		return login + "@webmail.com.br";
	}

	private void associaFiltrosPadrao(Usuario usuario,
			List<Filtro> filtros) {
		for (Filtro filtro : filtros) 
			filtroBean.associaFiltroUsuario(usuario, filtro);
	}
}
