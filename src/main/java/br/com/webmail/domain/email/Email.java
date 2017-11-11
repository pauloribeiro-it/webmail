package br.com.webmail.domain.email;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.com.webmail.domain.filtro.Filtro;
import br.com.webmail.domain.usuario.Usuario;

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
	
	@ManyToOne
	@JoinColumn(name="id_destinatario",referencedColumnName="id")
	private Usuario destinatario;
	
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
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_filtro",referencedColumnName="id")
	private Filtro filtro;
	
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
	
	public Filtro getFiltro() {
		return filtro;
	}

	public void setFiltro(Filtro filtro) {
		this.filtro = filtro;
	}

	public Usuario getDestinatario() {
		return destinatario;
	}

	public void setDestinatario(Usuario destinatario) {
		this.destinatario = destinatario;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
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
		if (id != other.id)
			return false;
		return true;
	}

}
