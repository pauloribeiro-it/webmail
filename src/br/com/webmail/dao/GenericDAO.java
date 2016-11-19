package br.com.webmail.dao;

import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.ResourceBundle;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

public abstract class GenericDAO<E,PK> implements DAOInterface<E, PK>{

	@PersistenceContext(unitName="webmail",type=PersistenceContextType.EXTENDED)
	protected EntityManager entityManager;
	
	protected ResourceBundle resource = ResourceBundle.getBundle(GenericDAO.class.getName());
	
	@SuppressWarnings("unchecked")
	@Override
	public E find(PK pk) {
		return (E)entityManager.find(getTypeClass(),pk);
	}

	@Override
	public void save(E e) {
		entityManager.persist(e);
	}

	@Override
	public void merge(E e) {
		entityManager.merge(e);
	}

	@Override
	public void delete(E e) {
		entityManager.remove(e);
	}

	@Override
	public void refresh(E e) {
		entityManager.refresh(e);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<E> findAll() {
		 return entityManager.createQuery(("FROM " + getTypeClass().getName()))
	                .getResultList();
	}

	private Class<?> getTypeClass() {
        Class<?> clazz = (Class<?>) ((ParameterizedType) this.getClass()
                .getGenericSuperclass()).getActualTypeArguments()[1];
        return clazz;
    }
}
