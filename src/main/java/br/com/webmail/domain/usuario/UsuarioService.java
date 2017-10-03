package br.com.webmail.domain.usuario;

import java.util.List;

public interface UsuarioService {
	 Usuario find(Long id);
	 void save(Usuario usuario, String senha);
	 void merge(Usuario usuario);
	 void delete(Usuario usuario);
	 List<Usuario> findAll();
}
