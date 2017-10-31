package br.com.webmail.domain.email;

import java.util.List;

import br.com.webmail.domain.usuario.Usuario;

public interface EmailService {
	void enviarEmail(Email email);
	List<Email> obtemEmailsPorUsuarioEFiltro(Usuario usuario, Long idFiltro);
	void moveEmailsParaLixeira(List<Email> emails);
}
