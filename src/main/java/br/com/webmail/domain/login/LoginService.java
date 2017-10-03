package br.com.webmail.domain.login;

public interface LoginService {
	void save(Login login);
	Login find(String login);
	void delete(Login login);
}
