package br.com.webmail.domain.usuario;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.com.webmail.domain.email.EmailFiltro;

@Entity
@Table(name="usuario")
public class Usuario {
	//<f:validateRegex pattern="[A-Za-z0-9\\._-]" />
	@Id
	@GeneratedValue
	private long id;
	@Column
	private String nome;
	@Column
	private String email;
	@Column(name="data_criacao")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataCriacao;
	@Column(name="ultimo_login")
	@Temporal(TemporalType.TIMESTAMP)
	private Date ultimoLogin;
	
	@OneToMany
	private List<EmailFiltro> filtros;
	
	public Usuario(){
		
	}
	
	public Usuario(String nome, String email) {
		this.nome = nome;
		this.email = email;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(Date dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public Date getUltimoLogin() {
		return ultimoLogin;
	}

	public void setUltimoLogin(Date ultimoLogin) {
		this.ultimoLogin = ultimoLogin;
	}

	public List<EmailFiltro> getFiltros() {
		return filtros;
	}

	public void setFiltros(List<EmailFiltro> filtros) {
		this.filtros = filtros;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((dataCriacao == null) ? 0 : dataCriacao.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((filtros == null) ? 0 : filtros.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result
				+ ((ultimoLogin == null) ? 0 : ultimoLogin.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		if (dataCriacao == null) {
			if (other.dataCriacao != null)
				return false;
		} else if (!dataCriacao.equals(other.dataCriacao))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (filtros == null) {
			if (other.filtros != null)
				return false;
		} else if (!filtros.equals(other.filtros))
			return false;
		if (id != other.id)
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (ultimoLogin == null) {
			if (other.ultimoLogin != null)
				return false;
		} else if (!ultimoLogin.equals(other.ultimoLogin))
			return false;
		return true;
	}

}
