package br.com.webmail.sessionbeans;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import br.com.web.sessionsbean.ifc.FiltroIFC;
import br.com.webmail.dao.DAOInterface;
import br.com.webmail.dao.FiltroDAO;
import br.com.webmail.dao.GenericDAO;
import br.com.webmail.entities.EmailFiltro;
import br.com.webmail.entities.Filtro;
import br.com.webmail.entities.Usuario;

@Stateless
public class FiltroSessionBean implements FiltroIFC{

	private FiltroDAO dao = new FiltroDAO();
	
	@Inject
	private DAOInterface<EmailFiltro, Long> emailFiltroDAO;
	
	@Override
	public List<Filtro> obtemFiltrosUsuario(Usuario usuario) {
		return dao.obtemFiltrosUsuario(usuario);
	}

	@Override
	public void associaFiltroUsuario(Usuario usuario, Filtro filtro) {
		EmailFiltro emailFiltro = new EmailFiltro(null,filtro,usuario);
		emailFiltroDAO.save(emailFiltro);
	}

	@Override
	public List<Filtro> obtemFiltrosPadrao() {
		return dao.obtemFiltrosPadrao();
	}

}
