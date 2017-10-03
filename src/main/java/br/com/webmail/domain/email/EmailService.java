package br.com.webmail.domain.email;

import java.util.List;

import javax.ejb.Local;

import br.com.webmail.domain.filtro.Filtro;

@Local
public interface EmailService {
	void save(Email email);
	void saveDestinatarios(List<EmailDestinatario> destinatarios);
	void saveEmailFiltro(Email email, List<Filtro> filtrosUsuario);
}
