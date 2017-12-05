package br.com.webmail.domain.usuario;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("nomeUsuarioValidator")
public class NomeUsuarioValidator implements Validator{
	
	public void validate(FacesContext faces, UIComponent component, Object obj) throws ValidatorException {
		String nomeUsuario = ((String) obj).trim();
		if(Character.isDigit(nomeUsuario.charAt(0)) || !nomeUsuario.matches("(\\w)+")){
			throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Formato de nome inválido. O nome não pode começar com número ou conter caracteres especiais.", null));
		}
	}

}
