package br.com.webmail.domain.filtro;

import javax.ejb.Stateless;

import br.com.webmail.dao.CrudDao;
import br.com.webmail.domain.email.EmailFiltro;

@Stateless
public class EmailFiltroDAO extends CrudDao<EmailFiltro, Long>{

	private static final long serialVersionUID = -5267843923764428561L;

}
