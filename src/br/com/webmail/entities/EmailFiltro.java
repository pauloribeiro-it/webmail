package br.com.webmail.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "email_filtro")
public class EmailFiltro implements Serializable {
	
	private static final long serialVersionUID = -999776900359062822L;

	@Id
	@GeneratedValue
	private Long id;

	@ManyToOne
	@JoinColumn(name = "id_email", referencedColumnName = "id")
	private Email email;

	@ManyToOne
	@JoinColumn(name = "id_filtro", referencedColumnName = "id")
	private Filtro filtro;

	@ManyToOne
	@JoinColumn(name = "id_usuario", referencedColumnName = "id")
	private Usuario usuario;

	public EmailFiltro() {

	}

	public EmailFiltro(Email email, Filtro filtro, Usuario usuario) {
		this.email = email;
		this.filtro = filtro;
		this.usuario = usuario;
	}

	public Email getEmail() {
		return email;
	}

	public void setEmail(Email email) {
		this.email = email;
	}

	public Filtro getFiltro() {
		return filtro;
	}

	public void setFiltro(Filtro filtro) {
		this.filtro = filtro;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((filtro == null) ? 0 : filtro.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((usuario == null) ? 0 : usuario.hashCode());
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
		EmailFiltro other = (EmailFiltro) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (filtro == null) {
			if (other.filtro != null)
				return false;
		} else if (!filtro.equals(other.filtro))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (usuario == null) {
			if (other.usuario != null)
				return false;
		} else if (!usuario.equals(other.usuario))
			return false;
		return true;
	}

}
