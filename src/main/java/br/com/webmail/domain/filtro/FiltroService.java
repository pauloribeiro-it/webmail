package br.com.webmail.domain.filtro;

import java.util.List;

import br.com.webmail.domain.usuario.Usuario;

public interface FiltroService {
	List<Filtro> obtemFiltrosUsuario(Usuario usuario);
	List<Filtro> obtemFiltrosPadrao();
	Filtro obtemFiltroPorId(Long idFiltro);
}
