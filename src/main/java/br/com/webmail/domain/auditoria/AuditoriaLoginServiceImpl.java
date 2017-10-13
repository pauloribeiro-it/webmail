package br.com.webmail.domain.auditoria;

import java.util.Date;

import javax.ejb.Stateless;
import javax.inject.Inject;

import br.com.webmail.domain.usuario.Usuario;
import br.com.webmail.util.WebmailUtil;

@Stateless
public class AuditoriaLoginServiceImpl implements AuditoriaLoginService{

	@Inject
	private AuditoriaLoginDao auditoriaDao;
	
	public AuditoriaLogin auditaLogin(Usuario usuario) {
		AuditoriaLogin auditoriaLogin = new AuditoriaLogin();
		auditoriaLogin.setDataLogin(new Date());
		auditoriaLogin.setUsuario(usuario);
		auditoriaLogin.setIdSessao(usuario.getIdSessao());
		return auditoriaDao.insert(auditoriaLogin);
	}

	public AuditoriaLogin obtemAuditoriaUsuario(Usuario usuario) {
		return auditoriaDao.obtemAuditoriaUsuario(usuario, WebmailUtil.getUsuarioSessao().getIdSessao());
	}
	
}
