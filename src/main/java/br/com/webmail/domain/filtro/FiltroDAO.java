package br.com.webmail.domain.filtro;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.Query;

import org.jboss.logging.Logger;

import br.com.webmail.dao.CrudDao;
import br.com.webmail.dao.EnumQueries;
import br.com.webmail.domain.email.Email;
import br.com.webmail.domain.email.EmailDAO;
import br.com.webmail.domain.usuario.Usuario;
import br.com.webmail.domain.usuario.UsuarioFiltro;

@Stateless
public class FiltroDAO extends CrudDao<Filtro, Long> {

	private static final long serialVersionUID = 3364837670567051036L;

	@Inject
	private EmailDAO emailDAO;

	private static final Logger logger = Logger.getLogger(FiltroDAO.class);

	public List<Email> obtemEmailsUsuario(Usuario usuario) {
		List<Email> emails = null;
		emails = emailDAO
				.obtemEmailsFromFiltros(obtemEmailFiltroUsuario(usuario));
		return emails;
	}

	@SuppressWarnings("unchecked")
	public List<UsuarioFiltro> obtemEmailFiltroUsuario(Usuario usuario) {
		List<UsuarioFiltro> emailFiltros = null;
		Query query = getEntityManager().createQuery(
				EnumQueries.OBTEMFILTROSUSUARIO.getQuery())
				.setParameter("usuario", usuario);
		emailFiltros = (List<UsuarioFiltro>) query.getResultList();
		return emailFiltros;
	}

	public List<Long> obtemEmailFiltroUsuario(List<UsuarioFiltro> filtrosUsuario) {
		List<Long> ids = new ArrayList<Long>();
		for (UsuarioFiltro filtroUsuario : filtrosUsuario){
			ids.add(filtroUsuario.getFiltro().getId());
		}
		return ids;
	}

	@SuppressWarnings("unchecked")
	public List<Filtro> obtemFiltrosUsuario(Usuario usuario) {
		List<Filtro> filtros = null;
		Query query = getEntityManager().createQuery(
				EnumQueries.FILTROUSUARIO.getQuery()).setParameter("idFiltro",
				obtemEmailFiltroUsuario(obtemEmailFiltroUsuario(usuario)));
		filtros = (List<Filtro>) query.getResultList();
		logger.info("Size = " + filtros.size());
		return filtros;
	}

	@SuppressWarnings("unchecked")
	public List<Filtro> obtemFiltrosPadrao() {
		List<Filtro> filtros = null;
		Query query = getEntityManager().createQuery(EnumQueries.FILTROPADRAO.getQuery());
		filtros = (List<Filtro>) query.getResultList();
		return filtros;
	}
}
