package br.com.webmail.domain.auditoria;

import java.util.Date;

import javax.ejb.Stateless;
import javax.inject.Inject;

import br.com.webmail.dao.CrudDao;
import br.com.webmail.domain.usuario.Usuario;

@Stateless
public class AuditoriaLoginServiceImpl implements AuditoriaLoginService{

	@Inject
	private CrudDao<AuditoriaLogin,Integer> auditoriaDao;
	
	@Override
	public void auditaLogin(Usuario usuario) {
		AuditoriaLogin auditoriaLogin = new AuditoriaLogin();
		auditoriaLogin.setDataLogin(new Date());
		auditoriaLogin.setUsuario(usuario);
		auditoriaLogin.setIdSessao(usuario.getIdSessao());
		auditoriaDao.insert(auditoriaLogin);
	}
	
}
