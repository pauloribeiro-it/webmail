package br.com.webmail.domain.filtro;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.Query;

import org.jboss.logging.Logger;

import br.com.webmail.dao.CrudDao;
import br.com.webmail.domain.email.Email;
import br.com.webmail.domain.email.EmailDAO;
import br.com.webmail.domain.email.EmailFiltro;
import br.com.webmail.domain.usuario.Usuario;

@Stateless
public class FiltroDAO extends CrudDao<Filtro, Long> {

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
	public List<EmailFiltro> obtemEmailFiltroUsuario(Usuario usuario) {
		List<EmailFiltro> emailFiltros = null;
		Query query = getEntityManager().createQuery(
				getQueryByFullName("EmailFiltro.obtemFiltrosUsuario"))
				.setParameter("usuario", usuario);
		emailFiltros = (List<EmailFiltro>) query.getResultList();
		return emailFiltros;
	}

	public List<Long> obtemEmailFiltroUsuario(List<EmailFiltro> emails) {
		List<Long> ids = new ArrayList<Long>();
		for (EmailFiltro emailFiltro : emails) 
			ids.add(emailFiltro.getFiltro().getId());
		return ids;
	}

	@SuppressWarnings("unchecked")
	public List<Filtro> obtemFiltrosUsuario(Usuario usuario) {
		List<Filtro> filtros = null;
		Query query = getEntityManager().createQuery(
				getQueryByFullName("Filtro.filtroUsuario")).setParameter(
				"idFiltro",
				obtemEmailFiltroUsuario(obtemEmailFiltroUsuario(usuario)));
		filtros = (List<Filtro>) query.getResultList();
		logger.info("Size = " + filtros.size());
		return filtros;
	}

	@SuppressWarnings("unchecked")
	public List<Filtro> obtemFiltrosPadrao() {
		List<Filtro> filtros = null;
		Query query = getEntityManager().createQuery(getQueryByFullName("Filtro.filtrosPadrao"));
		filtros = (List<Filtro>) query.getResultList();
		return filtros;
	}
}
