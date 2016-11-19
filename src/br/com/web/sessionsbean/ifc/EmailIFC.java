package br.com.web.sessionsbean.ifc;

import java.util.List;

import javax.ejb.Local;

import br.com.webmail.entities.Email;
import br.com.webmail.entities.EmailDestinatario;
import br.com.webmail.entities.Filtro;

@Local
public interface EmailIFC {
	void save(Email email);
	void saveDestinatarios(List<EmailDestinatario> destinatarios);
	void saveEmailFiltro(Email email, List<Filtro> filtrosUsuario);
}
