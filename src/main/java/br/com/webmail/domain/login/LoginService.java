package br.com.webmail.domain.login;

import br.com.webmail.domain.usuario.Usuario;

public interface LoginService {
	Login configuraPerfil(Usuario usuario, String senha);
	void save(Login login);
}
