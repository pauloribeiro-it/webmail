package br.com.webmail.domain.usuario;

import java.util.List;

import javax.ejb.Local;

@Local
public interface UsuarioIFC {
	 Usuario find(Long id);
	 void save(Usuario usuario, String senha);
	 void merge(Usuario usuario);
	 void delete(Usuario usuario);
	 List<Usuario> findAll();
}
