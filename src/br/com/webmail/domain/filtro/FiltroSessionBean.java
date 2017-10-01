package br.com.webmail.domain.filtro;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import br.com.webmail.domain.email.EmailFiltro;
import br.com.webmail.domain.usuario.Usuario;

@Stateless
public class FiltroSessionBean implements FiltroIFC{
	private FiltroDAO dao = new FiltroDAO(Filtro.class);
	
//	@Inject
//	private DAOInterface<EmailFiltro, Long> emailFiltroDAO;
	
	public List<Filtro> obtemFiltrosUsuario(Usuario usuario) {
		return dao.obtemFiltrosUsuario(usuario);
	}

	public void associaFiltroUsuario(Usuario usuario, Filtro filtro) {
		EmailFiltro emailFiltro = new EmailFiltro(null,filtro,usuario);
//		emailFiltroDAO.save(emailFiltro);
	}

	public List<Filtro> obtemFiltrosPadrao() {
		return dao.obtemFiltrosPadrao();
	}

}
