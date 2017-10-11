package br.com.webmail.domain.filtro;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import br.com.webmail.dao.CrudDao;
import br.com.webmail.dao.EnumQueries;
import br.com.webmail.domain.usuario.Usuario;

@Stateless
public class FiltroDAO extends CrudDao<Filtro, Long> {

	private static final long serialVersionUID = 3364837670567051036L;

	@SuppressWarnings("unchecked")
	public List<Filtro> obtemFiltrosPadraoUsuario(Usuario usuario) {
		List<Filtro> filtros = null;
		Query query = getEntityManager().createQuery(EnumQueries.FILTROUSUARIO.getQuery());
		filtros = (List<Filtro>) query.getResultList();
		return filtros;
	}

}
