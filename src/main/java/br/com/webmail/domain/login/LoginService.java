package br.com.webmail.domain.login;

import br.com.webmail.domain.usuario.Usuario;

public interface LoginService {
	Login configuraPerfil(Usuario usuario, String senha);
	void salvaLogin(Login login);
	void atualizaSenhaLogin(Login login);
	Login obtemLoginPorUsuario(Usuario usuario);
}
