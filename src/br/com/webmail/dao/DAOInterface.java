package br.com.webmail.dao;

import java.util.List;

public interface DAOInterface <E,PK>{
	E find(PK pk);
	void save(E e);
	void merge(E e);
	void delete(E e);
	void refresh(E e);
	List<E> findAll();
}
