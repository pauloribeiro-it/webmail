package br.com.webmail.domain.filtro;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import br.com.webmail.dao.CrudDao;
import br.com.webmail.dao.Dao;
import br.com.webmail.domain.usuario.Usuario;
import br.com.webmail.domain.usuario.UsuarioFiltro;

@Stateless
public class FiltroServiceImpl implements FiltroService{
	@Inject
	private FiltroDAO dao;
	
	@Inject @Dao
	private CrudDao<UsuarioFiltro, Long> emailFiltroDAO;
	
	public List<Filtro> obtemFiltrosUsuario(Usuario usuario) {
		return dao.obtemFiltrosUsuario(usuario);
	}

	public void associaFiltroUsuario(Usuario usuario, Filtro filtro) {
		UsuarioFiltro emailFiltro = new UsuarioFiltro(filtro,usuario);
		emailFiltroDAO.insert(emailFiltro);
	}

	public List<Filtro> obtemFiltrosPadrao() {
		return dao.findAll();
	}

}
