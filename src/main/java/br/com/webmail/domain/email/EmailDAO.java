package br.com.webmail.domain.email;

import java.util.List;

import javax.ejb.Stateless;

import br.com.webmail.dao.CrudDao;
import br.com.webmail.dao.EnumQueries;
import br.com.webmail.domain.usuario.Usuario;
import br.com.webmail.enums.EnumFiltro;

@Stateless
public class EmailDAO extends CrudDao<Email, Long> {
	
	private static final long serialVersionUID = 3791199770182376882L;

	public EmailDAO(){
		
	}
	
	@SuppressWarnings("unchecked")
	public List<Email> obtemEmailsPorUsuarioEPorFiltro(Usuario usuario, Long idFiltro){
		List<Email> emails = null;
		emails = (List<Email>) getEntityManager().createQuery(EnumQueries.OBTEMEMAILPORUSUARIOEFILTRO.getQuery()).
				setParameter("idFiltro", idFiltro).
				setParameter("idDestinatario",usuario.getId()).
				getResultList();
		return emails;
	}
	
	@SuppressWarnings("unchecked")
	public List<Email> obtemRascunhosUsuario(Usuario usuario){
		List<Email> emails = null;
		emails = (List<Email>) getEntityManager().createQuery(EnumQueries.OBTEMRASCUNHOS.getQuery()).
				setParameter("idFiltro", EnumFiltro.RASCUNHOS.getValor()).
				setParameter("idUsuario", usuario.getId()).getResultList();
		return emails;
	}
	
}