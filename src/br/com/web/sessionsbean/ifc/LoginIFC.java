package br.com.web.sessionsbean.ifc;

import javax.ejb.Local;

import br.com.webmail.entities.Login;

@Local
public interface LoginIFC {
	void save(Login login);
	Login find(String login);
	void delete(Login login);
}
