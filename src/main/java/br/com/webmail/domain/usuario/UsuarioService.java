package br.com.webmail.domain.usuario;

public interface UsuarioService {
	 void registraUsuario(Usuario usuario, String senha);
	 Usuario findByLogin(String login);
}
