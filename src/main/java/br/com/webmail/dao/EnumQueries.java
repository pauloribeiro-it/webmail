package br.com.webmail.dao;

public enum EnumQueries {
	
	//Email Filtro
	EMAILFROMFILTRO("select e from Email e where e.id in (:emailFiltro)"),
	
	//Filtro
	FILTROUSUARIO("select f from Filtro f "),
	
	//Usuário
	FINDUSUARIOBYLOGIN("select u from Usuario u where u.email=:login"),
	
	OBTEMDESTINATARIOEMAILPOREMAILEDESTINATARIO("select ed from EmailDestinatario ed where ed.email in(:emails) and ed.destinatario = :destinatario"),
	
	//Email
	OBTEMEMAILPORUSUARIOEFILTRO("select e from Email e inner join e.filtro f inner join e.destinatario d "+
			" where f.id=:idFiltro and d.id=:idDestinatario"),
	
	OBTEMRASCUNHOS("select e from Email e inner join e.filtro f inner join e.remetente u"+
			" where f.id=:idFiltro and u.id=:idUsuario"),
	
	//Auditoria Login
	OBTEMAUDITORIALOGINPORUSUARIO("select a from AuditoriaLogin a inner join a.usuario u where u.id=:idUsuario and a.idSessao=:idSessao"),
	
	//Login
	OBTEMLOGINPORUSUARIOLOGADO("select l from Login l where l.usuario.id =:idUsuario");
	
	private String query;
	
	private EnumQueries(String query) {
		this.query = query;
	}
	
	public String getQuery() {
		return query;
	}
		
}
