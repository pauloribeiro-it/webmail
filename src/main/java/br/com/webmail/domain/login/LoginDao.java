package br.com.webmail.domain.login;

import javax.ejb.Stateless;
import javax.persistence.NoResultException;

import br.com.webmail.dao.CrudDao;
import br.com.webmail.dao.EnumQueries;
import br.com.webmail.domain.usuario.Usuario;

@Stateless
public class LoginDao extends CrudDao<Login,String>{

	private static final long serialVersionUID = -5342692108614494457L;
	
	public Login obtemLoginPorUsuario(Usuario usuario){
		Login login = null;
		try{
			login = (Login)getEntityManager().createQuery(EnumQueries.OBTEMLOGINPORUSUARIOLOGADO.getQuery())
					.setParameter("idUsuario", usuario.getId()).getSingleResult();
		}catch(NoResultException e){
			e.printStackTrace();
		}
		return login;
	}
}
