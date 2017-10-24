package br.com.webmail.domain.email;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.com.webmail.domain.usuario.Usuario;

@Entity
@Table(name="email_destinatario")
public class EmailDestinatario implements Serializable{

	private static final long serialVersionUID = -214016089090102294L;

	@Id
	@ManyToOne
	@JoinColumn(name="id_email",referencedColumnName="id")
	private Email email;
	
	@Id
	@ManyToOne
	@JoinColumn(name="id_destinatario",referencedColumnName="id")
	private Usuario destinatario;
	
	public EmailDestinatario(){
		
	}

	public Email getEmail() {
		return email;
	}

	public void setEmail(Email email) {
		this.email = email;
	}

	public Usuario getUsuario() {
		return destinatario;
	}

	public void setUsuario(Usuario destinatario) {
		this.destinatario = destinatario;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((destinatario == null) ? 0 : destinatario.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
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
		EmailDestinatario other = (EmailDestinatario) obj;
		if (destinatario == null) {
			if (other.destinatario != null)
				return false;
		} else if (!destinatario.equals(other.destinatario))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		return true;
	}
	
}
