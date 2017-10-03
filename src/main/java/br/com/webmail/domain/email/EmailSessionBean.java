package br.com.webmail.domain.email;

import java.util.List;

import javax.ejb.Stateless;

import br.com.webmail.domain.filtro.Filtro;

@Stateless
public class EmailSessionBean implements EmailService {
//	@Inject
//	private EmailDAO dao;
//
//	@Inject
//	private DAOInterface<EmailDestinatario, Email> destinatarioDAO;
//
//	@Inject
//	private DAOInterface<EmailFiltro, Long> emailFiltroDAO;

	public void save(Email email) {
//		dao.save(email);
	}

	public void update(Email email) {
//		dao.merge(email);
	}

	@Override
	public void saveDestinatarios(List<EmailDestinatario> destinatarios) {
		for (EmailDestinatario destinatario : destinatarios){
//			destinatarioDAO.save(destinatario);
		}
	}

	@Override
	public void saveEmailFiltro(Email email, List<Filtro> filtrosUsuario) {
		EmailFiltro emailFiltro = constroiEmailFiltro(email,
				findFiltro(email, filtrosUsuario));
		// no momento em que for salvar o email_filtro verificar se há um
		// email_filtro com email null, caso existir atualiza-lo com o novo
		// email
//		emailFiltroDAO.save(emailFiltro);
	}

	private Filtro findFiltro(Email email, List<Filtro> filtros) {
		Filtro filtro = null;
		for (Filtro f : filtros)
			if (f.getNome().equalsIgnoreCase("Caixa de Entrada"))
				filtro = f;

		for (Filtro f : filtros) {
			String regras[] = f.getRegra().split(";");
			if (regras != null) {
				for (String str : regras) {
					String valorFiltro = str.split("=")[1];

					if ((str.equalsIgnoreCase("Email.corpo") && valorFiltro
							.contains(email.getCorpo()))
							|| (str.equalsIgnoreCase("Email.assunto") && valorFiltro
									.contains(email.getAssunto()))
							|| (str.equalsIgnoreCase("Email.corpo") && valorFiltro
									.equals(email.getRemetente()))) {
						filtro = f;
					}
				}
			}
		}

		return filtro;

	}

	private EmailFiltro constroiEmailFiltro(Email email, Filtro filtro) {
		EmailFiltro emailFiltro = new EmailFiltro();
		emailFiltro.setEmail(email);
		emailFiltro.setUsuario(email.getRemetente());
		emailFiltro.setFiltro(filtro);
		return emailFiltro;
	}

}
