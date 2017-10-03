package br.com.webmail.domain.autorizacao;

import br.com.webmail.domain.login.Login;

public interface AutorizacaoService {
	void save(Login login);
	Autorizacao find(Login login);
	void delete(Login login);
}
