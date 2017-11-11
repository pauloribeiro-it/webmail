package br.com.webmail.domain.email;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.transaction.Transactional;

import br.com.webmail.domain.filtro.Filtro;
import br.com.webmail.domain.filtro.FiltroService;
import br.com.webmail.domain.usuario.Usuario;
import br.com.webmail.enums.EnumFiltro;
import br.com.webmail.interceptors.AuditOperacao;

@Stateless
@AuditOperacao
public class EmailServiceImpl implements EmailService {
	@Inject
	private EmailDAO dao;
	
//	@Inject @Dao
//	private CrudDao<EmailDestinatario, Email> destinatarioDAO;
	
	@EJB
	private FiltroService filtroService;
	
	@Transactional
	public void enviarEmail(Email email) {
		
		//Configura propriedades 
		configuraDatasEmail(email);
		
		//Insere email
		dao.insert(email);
		
		//Associa filtros ao email salvo
		configuraEmailFiltro(email);	
	}

	public void update(Email email) {
		dao.update(email);
	}

	/**
	 * quando for enviar o email selecionar apenas os filtros Caixa de Entrada 
	 * @param email
	 * @param filtrosUsuario
	 */
	private void configuraEmailFiltro(Email email) {
		List<Filtro> filtros = filtroService.obtemFiltrosUsuario(email.getRemetente());
		
		filtros.removeIf(f->!EnumFiltro.CAIXA_ENTRADA.getValor().equals(f.getId()));
		
		email.setFiltro(filtros.get(0));
		
		dao.update(email);
	}

	public List<Email> obtemEmailsPorUsuarioEFiltro(Usuario usuario, Long idFiltro) {
		if(EnumFiltro.RASCUNHOS.getValor().equals(idFiltro)){
			return dao.obtemRascunhosUsuario(usuario);
		}
		return dao.obtemEmailsPorUsuarioEPorFiltro(usuario, idFiltro);
	}
	
	private void configuraDatasEmail(Email email){
		email.setDataHoraCriacao(new Date());
		email.setDataHoraDeletado(null);
		email.setDataHoraEnviado(new Date());
		email.setDataHoraExcluido(null);
		email.setDataHoraLido(null);
		email.setDataHoraRecebido(null);
	}

	public void moveEmailsParaLixeira(List<Email> emails) {
		Filtro filtro = filtroService.obtemFiltroPorId(EnumFiltro.LIXO.getValor());
		emails.forEach(e->e.setFiltro(filtro));
		emails.forEach(e->dao.update(e));
	}

	public void excluiEmails(List<Email> emails, Usuario destinatario) {
		List<Long> ids = obtemIdsEmails(emails);
	}
	
	private List<Long> obtemIdsEmails(List<Email> emails){
		return emails.stream().mapToLong(e -> e.getId()).boxed().collect(Collectors.toList());
 	}
}
