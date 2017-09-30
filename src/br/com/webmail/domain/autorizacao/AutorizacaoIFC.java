package br.com.webmail.domain.autorizacao;

import javax.ejb.Local;

import br.com.webmail.domain.login.Login;

@Local
public interface AutorizacaoIFC {
	void save(Login login);
	Autorizacao find(Login login);
	void delete(Login login);
}
