package br.com.webmail.domain.auditoria;

import javax.ejb.Stateless;
import javax.inject.Inject;

import br.com.webmail.dao.CrudDao;
import br.com.webmail.dao.Dao;

@Stateless
public class AuditoriaOperacaoServiceImpl implements AuditoriaOperacaoService{

	@Inject @Dao
	private CrudDao<AuditoriaOperacao,Integer> auditoriaOperacaoDao;
	
	public void realizaAuditoriaOperacao(AuditoriaOperacao auditoriaOperacao) {
		auditoriaOperacaoDao.insert(auditoriaOperacao);
	}
	
}
