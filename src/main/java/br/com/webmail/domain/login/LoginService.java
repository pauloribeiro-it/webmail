package br.com.webmail.domain.login;

import br.com.webmail.domain.apoio.SenhaInvalidaException;
import br.com.webmail.domain.usuario.Usuario;

public interface LoginService {
	Login configuraPerfil(Usuario usuario, String senha);
	void salvaLogin(Login login);
	void atualizaSenhaLogin(Login login,String senhaAtual,String senhaNova) throws SenhaInvalidaException;
	Login obtemLoginPorUsuario(Usuario usuario);
}
