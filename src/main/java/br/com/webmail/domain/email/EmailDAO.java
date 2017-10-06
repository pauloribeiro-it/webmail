package br.com.webmail.domain.email;

import java.util.List;

import javax.ejb.Stateless;

import br.com.webmail.dao.CrudDao;
import br.com.webmail.dao.EnumQueries;
import br.com.webmail.domain.usuario.Usuario;
import br.com.webmail.domain.usuario.UsuarioFiltro;

@Stateless
public class EmailDAO extends CrudDao<Email, Long> {
	
	private static final long serialVersionUID = 3791199770182376882L;

	public EmailDAO(){
		
	}
	
	@SuppressWarnings("unchecked")
	public List<Email> obtemEmailsFromFiltros(List<UsuarioFiltro> filtros) {
		List<Email> emails = null;
		emails = (List<Email>) getEntityManager().createQuery(
				EnumQueries.EMAILFROMFILTRO.getQuery()).setParameter(1,filtros);
		return emails;
	}
	
	@SuppressWarnings("unchecked")
	public List<Email> obtemEmailsPorUsuarioEPorFiltro(Usuario usuario, String nomeFiltro){
		List<Email> emails = null;
		emails = (List<Email>) getEntityManager().createQuery(EnumQueries.OBTEMEMAILPORUSUARIOEFILTRO.getQuery()).
				setParameter(1, nomeFiltro).
				setParameter(2, usuario.getEmail());
		return emails;
	}
	
}