package br.com.webmail.domain.email;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.com.webmail.domain.usuario.Usuario;

@Entity
@Table(name = "amigo")
public class Amigo implements Serializable{
	private static final long serialVersionUID = -7710141528006156026L;

	@Id
	@ManyToOne
	@JoinColumn(name = "id_usuario1", referencedColumnName = "id")
	private Usuario usuario1;

	@Id
	@ManyToOne
	@JoinColumn(name = "id_usuario2", referencedColumnName = "id")
	private Usuario usuario2;

	@Column(name = "habilitado")
	private boolean isHabilitado;

	public Amigo() {

	}

	public boolean isHabilitado() {
		return isHabilitado;
	}

	public void setHabilitado(boolean isHabilitado) {
		this.isHabilitado = isHabilitado;
	}

	public Usuario getUsuario1() {
		return usuario1;
	}

	public void setUsuario1(Usuario usuario1) {
		this.usuario1 = usuario1;
	}

	public Usuario getUsuario2() {
		return usuario2;
	}

	public void setUsuario2(Usuario usuario2) {
		this.usuario2 = usuario2;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (isHabilitado ? 1231 : 1237);
		result = prime * result
				+ ((usuario1 == null) ? 0 : usuario1.hashCode());
		result = prime * result
				+ ((usuario2 == null) ? 0 : usuario2.hashCode());
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
		Amigo other = (Amigo) obj;
		if (isHabilitado != other.isHabilitado)
			return false;
		if (usuario1 == null) {
			if (other.usuario1 != null)
				return false;
		} else if (!usuario1.equals(other.usuario1))
			return false;
		if (usuario2 == null) {
			if (other.usuario2 != null)
				return false;
		} else if (!usuario2.equals(other.usuario2))
			return false;
		return true;
	}
	
}
