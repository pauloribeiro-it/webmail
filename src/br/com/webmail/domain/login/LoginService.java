package br.com.webmail.domain.login;

import javax.ejb.Local;

@Local
public interface LoginService {
	void save(Login login);
	Login find(String login);
	void delete(Login login);
}
