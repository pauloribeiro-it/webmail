package br.com.webmail.domain.filtro;

import java.util.List;

import javax.ejb.Local;

import br.com.webmail.domain.usuario.Usuario;

@Local
public interface FiltroIFC {
	List<Filtro> obtemFiltrosUsuario(Usuario usuario);
	List<Filtro> obtemFiltrosPadrao();
	void associaFiltroUsuario(Usuario usuario, Filtro filtro);
}
