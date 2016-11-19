package br.com.web.sessionsbean.ifc;

import javax.ejb.Local;

import br.com.webmail.entities.Autorizacao;
import br.com.webmail.entities.Login;

@Local
public interface AutorizacaoIFC {
	void save(Login login);
	Autorizacao find(Login login);
	void delete(Login login);
}
