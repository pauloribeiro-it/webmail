package br.com.webmail.domain.auditoria;

import br.com.webmail.domain.usuario.Usuario;

public interface AuditoriaLoginService {
	void auditaLogin(Usuario usuario);
}
