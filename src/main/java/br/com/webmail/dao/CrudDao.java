package br.com.webmail.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;

@Named("crudDao")
public class CrudDao<T,PK> implements Serializable {
	private static final long serialVersionUID = 1L;
	@Inject
    protected BaseDao<T,PK> dao;
    
    protected Class<T> entityClass;
    
   public Class<T> getEntityClass() {
        if (entityClass == null) {
            //only works if one extends BaseDao, we will take care of it with CDI
            entityClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        }
        return entityClass;
    }

    public void setEntityClass(Class<T> entityClass) {
        this.entityClass = entityClass;
    }
   
    public EntityManager getEntityManager(){
        return dao.getEntityManager();
    }
    
    public T find(PK id){
        return dao.find(id, getEntityClass());
    }
    
    public void delete(PK id){
         dao.delete(id, getEntityClass());
    }
    
    public T update(T t){
        return dao.update(t);
    }
    
    public void insert(T t){
        dao.insert(t);
    }
    
    public List<T> findAll(){
        return dao.findAll(getEntityClass());
    }
    
    public List<T> findWithNamedQuery(String namedQueryName){
        return dao.findWithNamedQuery(namedQueryName);
    }
    
    protected String getQueryByName(String name){
		return EnumQueries.findQueryByName(name).getQuery();
	}
	
	protected String getQueryByFullName(String name){
		return EnumQueries.findQueryByFullName(name).getQuery();
	}
}
