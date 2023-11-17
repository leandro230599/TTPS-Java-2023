package ttps.spring.clasesDAOImpl;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

import org.springframework.transaction.annotation.Transactional;

import ttps.spring.clasesDAO.GenericDAO;

@Transactional
public class GenericDAOHibernateJPA<T> implements GenericDAO<T> {

	protected Class<T> persistentClass;
	private EntityManager entityManager;

	public GenericDAOHibernateJPA(Class<T> clase) {
		persistentClass = clase;
	}

	public Class<T> getPersistentClass(){
		return this.persistentClass;
	}
	
	@Override
	public T persistir(T entity) {
		this.getEntityManager().persist(entity);
		this.flushAndClear();
		return entity;
	}

	public T actualizar(T entity) {
		this.getEntityManager().merge(entity);
		this.flushAndClear();
		return entity;
	}
	
	@Override
	public void borrar(T entity) {
		this.getEntityManager().remove(entity);
		this.flushAndClear();
	}

	@Override
	public T borrar(Long id) {
		T entity = this.getEntityManager().find(this.getPersistentClass(), id);
		if (entity != null) {
			this.getEntityManager().remove(entity);
		}
		this.flushAndClear();
		return entity;
	}

	@Override
	public List<T> recuperarTodos(String columnOrder) {
		Query consulta=
				this.getEntityManager().createQuery("select e from "
													  + getPersistentClass().getSimpleName()
													  + " e order by e."
													  + columnOrder);
		List<T> resultado = (List<T>)consulta.getResultList();
		return resultado;
	}

	@Override
	public boolean existe(Long id) {
		T entity = this.getEntityManager().find(this.getPersistentClass(), id);
		boolean result = false;
		if (entity != null) {
			result = true;
		}
		return result;
	}

	@Override
	public T recuperar(Serializable id) {
		 Query consulta = this.getEntityManager()
				 					  .createQuery("SELECT t FROM "+getPersistentClass().getSimpleName()
				 							  	  +" t WHERE t.id = :id");
		 consulta.setParameter("id", id);
		 T resultado = (T) consulta.getSingleResult();
		 return resultado;
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}

	@PersistenceContext
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	public void flushAndClear() {
		this.getEntityManager().flush();
		this.getEntityManager().clear();
	}
}
