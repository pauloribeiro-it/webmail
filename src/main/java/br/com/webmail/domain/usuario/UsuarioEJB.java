package br.com.webmail.domain.usuario;

import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;

import br.com.webmail.dao.CrudDao;
import br.com.webmail.dao.Dao;
import br.com.webmail.domain.filtro.Filtro;
import br.com.webmail.domain.filtro.FiltroService;
import br.com.webmail.domain.login.Login;
import br.com.webmail.domain.login.LoginService;
import static br.com.webmail.util.WebmailUtil.*;

@Stateless
public class UsuarioEJB implements UsuarioService {

	@Inject
	@Dao
	private CrudDao<Usuario, Long> usuarioDao;

	@EJB
	private LoginService loginBean;

	@EJB
	private FiltroService filtroBean;

	public void registraUsuario(Usuario usuario, String senha) {
		Login login = loginBean.configuraPerfil(usuario, getEncryptedPassword(senha));
		configuraDatas(usuario);
		usuario.setEmail(getEmailFormatado(usuario.getEmail()));
		usuarioDao.insert(usuario);
		loginBean.save(login);
		associaFiltrosPadrao(usuario, filtroBean.obtemFiltrosPadrao());
	}

	private void associaFiltrosPadrao(Usuario usuario, List<Filtro> filtros) {
		for (Filtro filtro : filtros) {
			filtroBean.associaFiltroUsuario(usuario, filtro);
		}
	}
	
	private void configuraDatas(Usuario usuario) {
		usuario.setDataCriacao(new Date());
		usuario.setUltimoLogin(new Date());
	}
	
}
