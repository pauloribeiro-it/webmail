package br.com.webmail.domain.filtro;

import java.util.List;

import br.com.webmail.domain.usuario.Usuario;
import br.com.webmail.domain.usuario.UsuarioFiltro;

public interface FiltroService {
	List<Filtro> obtemFiltrosUsuario(Usuario usuario);
	UsuarioFiltro obtemFiltroUsuario(Usuario usuario,Filtro filtro);
	List<Filtro> obtemFiltrosPadrao();
	void associaFiltroUsuario(Usuario usuario, Filtro filtro);
}
