package br.com.webmail.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.EntityManager;

@Named("daoGeneric")
@Stateless
public class DAO <E, PK>{
	protected EntityManager entityManager;
	private Class<E> especializacao;
	
	public DAO(){
		
	}
	
	public DAO(Class<E> especializacao){
		this.especializacao = especializacao;
	}
	
	public DAO(Class<E> especializacao,EntityManager em){
		this.especializacao = especializacao;
		this.entityManager = em;
	}
	
	public E find(PK pk) {
		return (E) entityManager.find(especializacao, pk);
	}

	public void save(E e) {
		entityManager.persist(e);
	}

	public void merge(E e) {
		entityManager.merge(e);
	}

	public void delete(E e) {
		entityManager.remove(e);
	}

	public void refresh(E e) {
		entityManager.refresh(e);
	}

	@SuppressWarnings("unchecked")
	public List<E> findAll() {
		return entityManager.createQuery(("FROM " + especializacao.getName())).getResultList();
	}

//	private Class<?> getTypeClass() {
//		Class<?> clazz = (Class<?>) ((ParameterizedType) this.getClass().getGenericSuperclass())
//				.getActualTypeArguments()[1];
//		return clazz;
//	}
	protected String getQueryByName(String name){
		return EnumQueries.findQueryByName(name).getQuery();
	}
	
	protected String getQueryByFullName(String name){
		return EnumQueries.findQueryByFullName(name).getQuery();
	}
}
