package br.com.webmail.domain.auditoria;

import javax.ejb.Stateless;

import br.com.webmail.dao.CrudDao;
import br.com.webmail.dao.EnumQueries;
import br.com.webmail.domain.usuario.Usuario;

@Stateless
public class AuditoriaLoginDao extends CrudDao<AuditoriaLogin,Integer>{

	private static final long serialVersionUID = 949261421810108945L;
	
	public AuditoriaLoginDao(){
		
	}
	
	public AuditoriaLogin obtemAuditoriaUsuario(Usuario usuario,String idSessao){
		return (AuditoriaLogin) getEntityManager().createQuery(EnumQueries.OBTEMAUDITORIALOGINPORUSUARIO.getQuery())
		.setParameter("idUsuario",usuario.getId())
		.setParameter("idSessao", idSessao)
		.getSingleResult();
	}
}
