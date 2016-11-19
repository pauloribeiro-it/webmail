package br.com.webmail.entities;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="email")
public class Email {
	@Id
	@GeneratedValue
	@Column(name="id")
	private long id;

	@Column(name="assunto")
	private String assunto;
	
	@Column(name="corpo")
	private String corpo;
	
	@ManyToOne
	@JoinColumn(name="id_remetente",referencedColumnName="id")
	private Usuario remetente;
	
	@OneToMany(mappedBy="email")
	private List<EmailDestinatario> destinatarios;
	
	@Column(name="data_hora_criacao")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataHoraCriacao;
	
	@Column(name="data_hora_lido")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataHoraLido;
	
	@Column(name="data_hora_recebido")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataHoraRecebido;
	
	@Column(name="data_hora_excluido")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataHoraExcluido;
	
	@Column(name="data_hora_deletado")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataHoraDeletado;
	
	@Column(name="data_hora_enviado")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataHoraEnviado;
	
	public Email (){
		
	}

	public Email(String assunto, String corpo, Usuario usuario) {
		this.assunto = assunto;
		this.corpo = corpo;
		this.remetente = usuario;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getAssunto() {
		return assunto;
	}

	public void setAssunto(String assunto) {
		this.assunto = assunto;
	}

	public String getCorpo() {
		return corpo;
	}

	public void setCorpo(String corpo) {
		this.corpo = corpo;
	}

	public Date getDataHoraCriacao() {
		return dataHoraCriacao;
	}

	public void setDataHoraCriacao(Date dataHoraCriacao) {
		this.dataHoraCriacao = dataHoraCriacao;
	}

	public Date getDataHoraLido() {
		return dataHoraLido;
	}

	public void setDataHoraLido(Date dataHoraLido) {
		this.dataHoraLido = dataHoraLido;
	}

	public Date getDataHoraRecebido() {
		return dataHoraRecebido;
	}

	public void setDataHoraRecebido(Date dataHoraRecebido) {
		this.dataHoraRecebido = dataHoraRecebido;
	}

	public Date getDataHoraExcluido() {
		return dataHoraExcluido;
	}

	public void setDataHoraExcluido(Date dataHoraExcluido) {
		this.dataHoraExcluido = dataHoraExcluido;
	}

	public Date getDataHoraDeletado() {
		return dataHoraDeletado;
	}

	public void setDataHoraDeletado(Date dataHoraDeletado) {
		this.dataHoraDeletado = dataHoraDeletado;
	}

	public Date getDataHoraEnviado() {
		return dataHoraEnviado;
	}

	public void setDataHoraEnviado(Date dataHoraEnviado) {
		this.dataHoraEnviado = dataHoraEnviado;
	}

	public Usuario getRemetente() {
		return remetente;
	}

	public void setRemetente(Usuario usuario) {
		this.remetente = usuario;
	}
	
	public List<EmailDestinatario> getDestinatarios() {
		return destinatarios;
	}

	public void setDestinatarios(List<EmailDestinatario> destinatarios) {
		this.destinatarios = destinatarios;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((assunto == null) ? 0 : assunto.hashCode());
		result = prime * result + ((corpo == null) ? 0 : corpo.hashCode());
		result = prime * result
				+ ((dataHoraCriacao == null) ? 0 : dataHoraCriacao.hashCode());
		result = prime
				* result
				+ ((dataHoraDeletado == null) ? 0 : dataHoraDeletado.hashCode());
		result = prime * result
				+ ((dataHoraEnviado == null) ? 0 : dataHoraEnviado.hashCode());
		result = prime
				* result
				+ ((dataHoraExcluido == null) ? 0 : dataHoraExcluido.hashCode());
		result = prime * result
				+ ((dataHoraLido == null) ? 0 : dataHoraLido.hashCode());
		result = prime
				* result
				+ ((dataHoraRecebido == null) ? 0 : dataHoraRecebido.hashCode());
		result = prime * result
				+ ((destinatarios == null) ? 0 : destinatarios.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((remetente == null) ? 0 : remetente.hashCode());
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
		Email other = (Email) obj;
		if (assunto == null) {
			if (other.assunto != null)
				return false;
		} else if (!assunto.equals(other.assunto))
			return false;
		if (corpo == null) {
			if (other.corpo != null)
				return false;
		} else if (!corpo.equals(other.corpo))
			return false;
		if (dataHoraCriacao == null) {
			if (other.dataHoraCriacao != null)
				return false;
		} else if (!dataHoraCriacao.equals(other.dataHoraCriacao))
			return false;
		if (dataHoraDeletado == null) {
			if (other.dataHoraDeletado != null)
				return false;
		} else if (!dataHoraDeletado.equals(other.dataHoraDeletado))
			return false;
		if (dataHoraEnviado == null) {
			if (other.dataHoraEnviado != null)
				return false;
		} else if (!dataHoraEnviado.equals(other.dataHoraEnviado))
			return false;
		if (dataHoraExcluido == null) {
			if (other.dataHoraExcluido != null)
				return false;
		} else if (!dataHoraExcluido.equals(other.dataHoraExcluido))
			return false;
		if (dataHoraLido == null) {
			if (other.dataHoraLido != null)
				return false;
		} else if (!dataHoraLido.equals(other.dataHoraLido))
			return false;
		if (dataHoraRecebido == null) {
			if (other.dataHoraRecebido != null)
				return false;
		} else if (!dataHoraRecebido.equals(other.dataHoraRecebido))
			return false;
		if (destinatarios == null) {
			if (other.destinatarios != null)
				return false;
		} else if (!destinatarios.equals(other.destinatarios))
			return false;
		if (id != other.id)
			return false;
		if (remetente == null) {
			if (other.remetente != null)
				return false;
		} else if (!remetente.equals(other.remetente))
			return false;
		return true;
	}
	
}
