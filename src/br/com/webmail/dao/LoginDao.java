package br.com.webmail.dao;

import javax.ejb.Stateless;

import br.com.webmail.domain.login.Login;

@Stateless
public class LoginDao extends CrudDao<Login,String>{

}
