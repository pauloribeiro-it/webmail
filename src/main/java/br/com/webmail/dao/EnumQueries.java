package br.com.webmail.dao;

import java.util.ArrayList;
import java.util.List;

import br.com.webmail.domain.auditoria.AuditoriaLogin;
import br.com.webmail.domain.email.Email;
import br.com.webmail.domain.filtro.Filtro;
import br.com.webmail.domain.login.Login;
import br.com.webmail.domain.usuario.Usuario;


public enum EnumQueries {
	//Email Filtro
	EMAILFROMFILTRO(Email.class,"emailFromFiltro","select e from Email e where e.id in (:emailFiltro)"),
	
	//Filtro
	FILTROUSUARIO(Filtro.class,"filtroUsuario",
			"select f from Filtro f "),
	
	//Usuário
	FINDUSUARIOBYLOGIN(Usuario.class,"findUsuarioByLogin","select u from Usuario u where u.email=:login"),
	
	OBTEMDESTINATARIOEMAILPOREMAILEDESTINATARIO(Email.class,"findDestinatarios","select ed from EmailDestinatario ed "+
																							" where ed.email in(:emails) and ed.destinatario = :destinatario"),
	
	//Email
	OBTEMEMAILPORUSUARIOEFILTRO(Email.class,"obtemEmailPorUsuarioEFiltro",
			"select e from Email e inner join e.filtro f inner join e.destinatario d "+
			" where f.id=:idFiltro and d.id=:idDestinatario"),
	
	OBTEMRASCUNHOS(Email.class,"obtemRascunhos","select e from Email e inner join e.filtro f inner join e.remetente u"+
			" where f.id=:idFiltro and u.id=:idUsuario"),
	
	//Auditoria Login
	OBTEMAUDITORIALOGINPORUSUARIO(AuditoriaLogin.class,"obtemAuditoriaLoginPorUsuario",
			"select a from AuditoriaLogin a inner join a.usuario u where u.id=:idUsuario and a.idSessao=:idSessao"),
	
	//Login
	OBTEMLOGINPORUSUARIOLOGADO(Login.class,"obtemLoginPorUsuarioLogado","select l from Login l where l.usuario.id =:idUsuario");
	
	private String queryName;
	private String query;
	private Class<?> destinationClass;
	
	private EnumQueries(Class<?> destinationClass,String queryName, String query) {
		this.queryName = queryName;
		this.query = query;
		this.destinationClass = destinationClass;
	}
	
	public String getQuery() {
		return query;
	}
	
	public String getQueryName() {
		return queryName;
	}
	
	public Class<?> getDestinationClass() {
		return destinationClass;
	}
	
	public String getQueryFullName(){
		return destinationClass.getSimpleName()+"."+queryName;
	}
	
	public static List<EnumQueries> findQueriesByEntity(Class<?> entity){
		List<EnumQueries> queries = new ArrayList<>();
		for(EnumQueries query:values()){
			if(query.equals(entity)){
				queries.add(query);
			}
		}
		return queries;
	}
	
	public static EnumQueries findQueryByName(String name){
		for(EnumQueries query:values()){
			if(query.getQueryName().equals(name)){
				return query;
			}
		}
		throw new IllegalArgumentException("Query :"+name+" não encontrada.");
	}
	
	public static EnumQueries findQueryByFullName(String name){
		for(EnumQueries query:values()){
			if(query.getQueryFullName().equals(name)){
				return query;
			}
		}
		throw new IllegalArgumentException("Query :"+name+" não encontrada.");
	}
}
