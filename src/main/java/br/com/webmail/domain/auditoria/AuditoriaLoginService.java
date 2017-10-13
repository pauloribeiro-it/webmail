package br.com.webmail.domain.auditoria;

import br.com.webmail.domain.usuario.Usuario;

public interface AuditoriaLoginService {
	AuditoriaLogin auditaLogin(Usuario usuario);
	AuditoriaLogin obtemAuditoriaUsuario(Usuario usuario);
}
