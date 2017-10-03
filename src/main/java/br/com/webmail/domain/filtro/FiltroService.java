package br.com.webmail.domain.filtro;

import java.util.List;

import javax.ejb.Local;

import br.com.webmail.domain.usuario.Usuario;

public interface FiltroService {
	List<Filtro> obtemFiltrosUsuario(Usuario usuario);
	List<Filtro> obtemFiltrosPadrao();
	void associaFiltroUsuario(Usuario usuario, Filtro filtro);
}
