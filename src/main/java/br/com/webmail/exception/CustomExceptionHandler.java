package br.com.webmail.exception;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Iterator;

import javax.faces.FacesException;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExceptionHandler;
import javax.faces.context.ExceptionHandlerWrapper;
import javax.faces.context.FacesContext;
import javax.faces.event.ExceptionQueuedEvent;
import javax.faces.event.ExceptionQueuedEventContext;

public class CustomExceptionHandler extends ExceptionHandlerWrapper {
	private static final Logger LOGGER = LogManager.getLogger(CustomExceptionHandler.class);
	private ExceptionHandler wrapped;

	CustomExceptionHandler(ExceptionHandler exception) {
		this.wrapped = exception;
	}

	@Override
	public ExceptionHandler getWrapped() {
		return wrapped;
	}

	@Override
	public void handle() throws FacesException {
		final Iterator<ExceptionQueuedEvent> i = getUnhandledExceptionQueuedEvents().iterator();
		while (i.hasNext()) {
			ExceptionQueuedEvent event = i.next();
			ExceptionQueuedEventContext context = (ExceptionQueuedEventContext) event.getSource();

			Throwable t = context.getException();

			final FacesContext fc = FacesContext.getCurrentInstance();

			try {
				LOGGER.error(t.getMessage(), t);
				fc.addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR, 
						"Erro", "Ops, ocorreu um erro. Procure o administrador para maiores detalhes."));
				fc.renderResponse();

			} finally {
				i.remove();
			}
		}
		getWrapped().handle();
	}
}
