package br.com.web.sessionsbean.ifc;

import java.util.List;

import javax.ejb.Local;

import br.com.webmail.entities.Filtro;
import br.com.webmail.entities.Usuario;

@Local
public interface FiltroIFC {
	List<Filtro> obtemFiltrosUsuario(Usuario usuario);
	List<Filtro> obtemFiltrosPadrao();
	void associaFiltroUsuario(Usuario usuario, Filtro filtro);
}
