package br.com.webmail.domain.auditoria;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="auditoria_operacao")
public class AuditoriaOperacao {
	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id")
	private Integer id;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_auditoria_login",referencedColumnName="id")
	private AuditoriaLogin auditoriaLogin;
	
	@Column(name="desc_operacao")
	private String descOperacao;
	
	@Column(name="data_operacao")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataOperacao;
	
	public AuditoriaOperacao(){
		
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public AuditoriaLogin getAuditoriaLogin() {
		return auditoriaLogin;
	}

	public void setAuditoriaLogin(AuditoriaLogin auditoriaLogin) {
		this.auditoriaLogin = auditoriaLogin;
	}

	public String getDescOperacao() {
		return descOperacao;
	}

	public void setDescOperacao(String descOperacao) {
		this.descOperacao = descOperacao;
	}

	public Date getDataOperacao() {
		return dataOperacao;
	}

	public void setDataOperacao(Date dataOperacao) {
		this.dataOperacao = dataOperacao;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		AuditoriaOperacao other = (AuditoriaOperacao) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}
