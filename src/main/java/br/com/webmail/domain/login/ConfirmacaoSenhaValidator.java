package br.com.webmail.domain.login;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.component.UIViewRoot;
import javax.faces.component.visit.VisitCallback;
import javax.faces.component.visit.VisitContext;
import javax.faces.component.visit.VisitResult;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("confirmacaoSenhaValidator")
public class ConfirmacaoSenhaValidator implements Validator{

	public void validate(FacesContext context, UIComponent uiComp, Object obj) throws ValidatorException {
	    UIViewRoot root = context.getViewRoot();
	    final UIComponent[] found = new UIComponent[1];

	    root.visitTree(VisitContext.createVisitContext(context), new VisitCallback() {     
	        @Override
	        public VisitResult visit(VisitContext context, UIComponent component) {
	            if(component.getId().equals("senhaNova")){
	                found[0] = component;
	                return VisitResult.COMPLETE;
	            }
	            return VisitResult.ACCEPT;              
	        }
	    });
	    
	    if(!obj.equals(((UIInput)found[0]).getValue())){
	    	throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Senha", "Verifique se a senha e a confirmação da senha estão iguais!"));
	    }
	}

}
