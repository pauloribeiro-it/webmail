package br.com.web.sessionsbean.ifc;

import java.util.List;

import javax.ejb.Local;

import br.com.webmail.entities.Usuario;

@Local
public interface UsuarioIFC {
	 Usuario find(Long id);
	 void save(Usuario usuario, String senha);
	 void merge(Usuario usuario);
	 void delete(Usuario usuario);
	 List<Usuario> findAll();
}
