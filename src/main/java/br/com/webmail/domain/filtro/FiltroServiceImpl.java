package br.com.webmail.domain.filtro;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import br.com.webmail.domain.usuario.Usuario;

@Stateless
public class FiltroServiceImpl implements FiltroService{
	@Inject
	private FiltroDAO dao;
	
	
	public List<Filtro> obtemFiltrosUsuario(Usuario usuario) {
		return dao.obtemFiltrosPadraoUsuario(usuario);
	}

	public List<Filtro> obtemFiltrosPadrao() {
		return dao.findAll();
	}

	public Filtro obtemFiltroPorId(Long idFiltro) {
		return dao.find(idFiltro);
	}

}
