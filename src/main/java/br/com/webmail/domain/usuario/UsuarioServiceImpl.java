package br.com.webmail.domain.usuario;

import static br.com.webmail.util.WebmailUtil.getEmailFormatado;
import static br.com.webmail.util.WebmailUtil.getEncryptedPassword;

import java.util.Date;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;

import br.com.webmail.domain.filtro.FiltroService;
import br.com.webmail.domain.login.Login;
import br.com.webmail.domain.login.LoginService;

@Stateless
public class UsuarioServiceImpl implements UsuarioService {

	@Inject
	private UsuarioDAO customUsuarioDao;
	
	@EJB
	private LoginService loginBean;

	@EJB
	private FiltroService filtroService;

	public void registraUsuario(Usuario usuario, String senha) {
		Login login = loginBean.configuraPerfil(usuario, getEncryptedPassword(senha));
		usuario.setDataCriacao(new Date());
		usuario.setEmail(getEmailFormatado(usuario.getEmail()));
		customUsuarioDao.insert(usuario);
		loginBean.save(login);
	}

	public Usuario findByLogin(String login) {
		return customUsuarioDao.findByLogin(login);
	}
	
}
