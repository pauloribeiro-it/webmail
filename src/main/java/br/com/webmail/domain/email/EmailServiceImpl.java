package br.com.webmail.domain.email;

import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;

import br.com.webmail.dao.CrudDao;
import br.com.webmail.dao.Dao;
import br.com.webmail.domain.filtro.Filtro;
import br.com.webmail.domain.filtro.FiltroService;
import br.com.webmail.domain.usuario.Usuario;
import br.com.webmail.domain.usuario.UsuarioFiltro;
import br.com.webmail.enums.EnumFiltro;

@Stateless
public class EmailServiceImpl implements EmailService {
	@Inject
	private EmailDAO dao;
	
	@Inject @Dao
	private CrudDao<EmailDestinatario, Email> destinatarioDAO;
	
	@Inject @Dao
	private CrudDao<UsuarioFiltro, Long> usuarioFiltroDAO;

	@EJB
	private FiltroService filtroService;
	
	public void enviarEmail(Email email) {
		
		//Obtém destinatários
		List<EmailDestinatario> destinatarios = email.getDestinatarios();
		email.setDestinatarios(null);
		
		//Configura propriedades 
		configuraDatasEmail(email);
		
		//Insere email
		dao.insert(email);
		
		//Salva destinatários
		saveDestinatarios(destinatarios);
		
		//Associa filtros ao email salvo
		configuraEmailFiltro(email);	
	}

	public void update(Email email) {
		dao.update(email);
	}

	private void saveDestinatarios(List<EmailDestinatario> destinatarios) {
		for (EmailDestinatario destinatario : destinatarios){
			destinatarioDAO.insert(destinatario);
		}
	}
	/**
	 * quando for enviar o email selecionar apenas os filtros Caixa de Entrada 
	 * @param email
	 * @param filtrosUsuario
	 */
	private void configuraEmailFiltro(Email email) {
		List<Filtro> filtros = filtroService.obtemFiltrosUsuario(email.getRemetente());
		
		filtros.removeIf(f->!EnumFiltro.CAIXA_ENTRADA.getValor().equals(f.getId().intValue()));
		
		UsuarioFiltro usuarioFiltro = filtroService.obtemFiltroUsuario(email.getRemetente(), filtros.get(0));
		
		email.setFiltrosUsuario(usuarioFiltro);
		
		dao.update(email);
	}

	public List<Email> obtemEmailsPorUsuarioEFiltro(Usuario usuario, Integer idFiltro) {
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
}
