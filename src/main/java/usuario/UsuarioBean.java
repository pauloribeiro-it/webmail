package br.com.webmail.domain.usuario;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.com.webmail.domain.filtro.Filtro;
import br.com.webmail.domain.filtro.FiltroService;
import br.com.webmail.domain.login.Login;
import br.com.webmail.domain.login.LoginService;

@Stateless
public class UsuarioBean implements UsuarioService {

//	@Inject
//	private GenericDAO<Usuario, Long> dao;

	@EJB
	private LoginService loginBean;

	@EJB
	private FiltroService filtroBean;

	public Usuario find(Long id) {
//		return dao.find(id);
		return null;
	}

	public void save(Usuario usuario, String senha) {
		Login login = configuraPerfil(usuario, senha);
		usuario.setEmail(getEmailFormatado(usuario.getEmail()));
//		dao.save(usuario);
		loginBean.save(login);
		associaFiltrosPadrao(usuario, filtroBean.obtemFiltrosPadrao());
	}

	public void merge(Usuario usuario) {
//		dao.merge(usuario);
	}

	public void delete(Usuario usuario) {
//		dao.delete(usuario);
	}

	public List<Usuario> findAll() {
//		return dao.findAll();
		return null;
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
