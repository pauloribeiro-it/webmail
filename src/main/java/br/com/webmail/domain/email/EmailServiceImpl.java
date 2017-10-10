package br.com.webmail.domain.email;

import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import br.com.webmail.dao.CrudDao;
import br.com.webmail.dao.Dao;
import br.com.webmail.domain.filtro.Filtro;
import br.com.webmail.domain.usuario.Usuario;
import br.com.webmail.domain.usuario.UsuarioFiltro;

@Stateless
public class EmailServiceImpl implements EmailService {
	@Inject
	private EmailDAO dao;
	
	@Inject @Dao
	private CrudDao<EmailDestinatario, Email> destinatarioDAO;
	
	@Inject @Dao
	private CrudDao<UsuarioFiltro, Long> usuarioFiltroDAO;

	public void enviarEmail(Email email) {
		configuraDatasEmail(email);
		dao.insert(email);
		saveDestinatarios(email.getDestinatarios());
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
	 * quando for enviar o email selecionar apenas os filtros Caixa de Entrada e Enviados
	 * @param email
	 * @param filtrosUsuario
	 */
	private void saveEmailFiltro(Email email, List<Filtro> filtrosUsuario) {
//		UsuarioFiltro emailFiltro = constroiEmailFiltro(email,
//				findFiltro(email, filtrosUsuario));
//		// no momento em que for salvar o email_filtro verificar se há um
//		// email_filtro com email null, caso existir atualiza-lo com o novo
//		// email
//		emailFiltroDAO.insert(emailFiltro);
	}

//	private EmailFiltro constroiEmailFiltro(Email email, Filtro filtro) {
//		EmailFiltro emailFiltro = new EmailFiltro();
//		emailFiltro.setEmail(email);
//		emailFiltro.setUsuario(email.getRemetente());
//		emailFiltro.setFiltro(filtro);
//		return emailFiltro;
//	}

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
