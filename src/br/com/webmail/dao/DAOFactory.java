package br.com.webmail.dao;

import java.lang.reflect.ParameterizedType;

import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;

public class DAOFactory<E,PK> {
	@Produces
	@DaoProduces
	public DAO<E,PK> create(InjectionPoint injectionPoint){
		ParameterizedType type = (ParameterizedType) injectionPoint.getType();
		@SuppressWarnings("unchecked")
		Class<E> classe = (Class<E>) type.getActualTypeArguments()[0];
		return new DAO<E,PK>(classe);
	}
}
