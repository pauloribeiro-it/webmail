package br.com.webmail.domain.email;

import java.util.List;

import br.com.webmail.domain.filtro.Filtro;

public interface EmailService {
	void save(Email email);
	void saveDestinatarios(List<EmailDestinatario> destinatarios);
	void saveEmailFiltro(Email email, List<Filtro> filtrosUsuario);
}
