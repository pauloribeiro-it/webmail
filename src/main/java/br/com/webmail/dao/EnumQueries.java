package br.com.webmail.dao;

import java.util.ArrayList;
import java.util.List;

import br.com.webmail.domain.auditoria.AuditoriaLogin;
import br.com.webmail.domain.email.Email;
import br.com.webmail.domain.filtro.Filtro;
import br.com.webmail.domain.usuario.Usuario;


public enum EnumQueries {
	//Email
	EMAILFROMFILTRO(Email.class,"emailFromFiltro","select e from Email e where e.id in (:emailFiltro)"),
	
	//Filtro
	FILTROUSUARIO(Filtro.class,"filtroUsuario",
			"select f from Filtro f "),
	
	//Usuário
	FINDUSUARIOBYLOGIN(Usuario.class,"findUsuarioByLogin","select u from Usuario u where u.email=:login"),
	
	//Email
	OBTEMEMAILPORUSUARIOEFILTRO(Email.class,"obtemEmailPorUsuarioEFiltro",
			"select e from Email e inner join e.filtro f inner join e.destinatarios d inner join d.destinatario du "+
			" where f.id=:idFiltro and du.email=:login"),
	
	OBTEMRASCUNHOS(Email.class,"obtemRascunhos","select e from Email e inner join e.filtro f inner join e.remetente u"+
			" where f.id=:idFiltro and u.id=:idUsuario"),
	
	//Auditoria Login
	OBTEMAUDITORIALOGINPORUSUARIO(AuditoriaLogin.class,"obtemAuditoriaLoginPorUsuario",
			"select a from AuditoriaLogin a inner join a.usuario u where u.id=:idUsuario and a.idSessao=:idSessao");
	
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
