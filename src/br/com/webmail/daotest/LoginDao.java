package br.com.webmail.daotest;

import javax.ejb.Stateless;

import br.com.webmail.domain.login.Login;

@Stateless
public class LoginDao extends CrudDao<Login,String>{

}
