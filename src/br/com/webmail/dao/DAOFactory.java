package br.com.webmail.dao;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;

import javax.ejb.Stateless;
import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.BeanManager;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

@Named("daofactory")
@Stateless
public class DAOFactory<E,PK> {
	
	@PersistenceContext(unitName = "webmail", type = PersistenceContextType.TRANSACTION)
	protected EntityManager entityManager;
	
	@Produces
	@Dependent
	@DaoProduces
	public DAO<E,PK> create(InjectionPoint injectionPoint,BeanManager beanManager){
		return new DAO<E,PK>(getEntityClass(injectionPoint),entityManager);
	}
	
	@SuppressWarnings("unchecked")
	@Produces
	@DaoCustomProduces
	public DAO<E,PK> createCustom(InjectionPoint injectionPoint){
//		Class<?> tipoInstancia = Class.forName(injectionPoint.getAnnotated().getBaseType().getTypeName());
//		tipoInstancia.getConstructor(Class.class,EntityManager.class).newInstance(getEntityClass(injectionPoint),entityManager);
		
		String fieldName = injectionPoint.getMember().getName();
		
		Field[] fields = injectionPoint.getMember().getDeclaringClass().getDeclaredFields();
		for(Field f:fields){
			if(fieldName.equals(f.getName())){
				try {
					return (DAO<E, PK>) Class.forName(f.getDeclaringClass().getName()).getConstructor(Class.class,EntityManager.class).newInstance(getEntityClass(injectionPoint),entityManager);
				} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
						| InvocationTargetException | NoSuchMethodException | SecurityException
						| ClassNotFoundException e) {
					e.printStackTrace();
				}
			}
		}
		throw new IllegalArgumentException("Deu ruim");
	}
	
	@SuppressWarnings("unchecked")
	private Class<E> getEntityClass(InjectionPoint injectionPoint){
		ParameterizedType type = (ParameterizedType) injectionPoint.getType();
		return (Class<E>) type.getActualTypeArguments()[0];
	}
}
