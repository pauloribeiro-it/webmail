package br.com.webmail.domain.login;

import javax.ejb.Stateless;

import br.com.webmail.dao.CrudDao;

@Stateless
public class LoginDao extends CrudDao<Login,String>{

}
