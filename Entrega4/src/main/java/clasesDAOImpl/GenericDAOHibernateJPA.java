package clasesDAOImpl;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import clasesDAO.GenericDAO;

public class GenericDAOHibernateJPA<T> implements GenericDAO<T> {

	protected Class<T> persistentClass;

	public GenericDAOHibernateJPA(Class<T> clase) {
		persistentClass = clase;
	}

	public Class<T> getPersistentClass(){
		return this.persistentClass;
	}
	
	@Override
	public T persistir(T entity) {
		/*EntityManager em = EMF.getEMF().createEntityManager();
		EntityTransaction tx = null;*/
		EntityManagerFactory entityManagerFactory=Persistence.createEntityManagerFactory("miUP");
		EntityManager entityManager=entityManagerFactory.createEntityManager();
		try {
			/*tx = em.getTransaction();
			tx.begin();
			em.persist(entity);
			System.out.println("Se guarda");
			tx.commit(); */
		    entityManager.getTransaction().begin();
		    entityManager.persist(entity);
		    entityManager.getTransaction().commit();
		} catch (RuntimeException e) {
			/*if (tx != null && tx.isActive())
				tx.rollback();*/
			System.out.println("NO SE GUARDO");
			throw e; // escribir en un log o mostrar un mensaje
		} finally {
			//em.close();
		    entityManager.close();
		    entityManagerFactory.close();
		}
		return entity;
	}

	public T actualizar(T entity) {
		EntityManager em= EMF.getEMF().createEntityManager();
		EntityTransaction etx= em.getTransaction();
		etx.begin();
		T entityMerged = em.merge(entity);
		etx.commit();
		em.close();
		return entityMerged;
	}
	
	@Override
	public void borrar(T entity) {
		EntityManager em = EMF.getEMF().createEntityManager();
		EntityTransaction tx = null;
		try {
			tx = em.getTransaction();
			tx.begin();
			em.remove(em.merge(entity));
			tx.commit();
		} catch (RuntimeException e) {
			if (tx != null && tx.isActive())
				tx.rollback();
			throw e; // escribir en un log o mostrar un mensaje
		} finally {
			em.close();
		}
	}

	@Override
	public T borrar(Long id) {
		EntityManager em = EMF.getEMF().createEntityManager();
		T entity = em.find(this.getPersistentClass(), id);
		if (entity != null) {
			em.remove(entity);
		}
		em.close();
		return entity;
	}

	@Override
	public List<T> recuperarTodos(String columnOrder) {
		Query consulta=
		EMF.getEMF().createEntityManager().createQuery("select e from "
													  + getPersistentClass().getSimpleName()
													  + " e order by e."
													  + columnOrder);
		List<T> resultado = (List<T>)consulta.getResultList();
		return resultado;
	}

	@Override
	public boolean existe(Long id) {
		EntityManager em = EMF.getEMF().createEntityManager();
		T entity = em.find(this.getPersistentClass(), id);
		boolean result = false;
		if (entity != null) {
			result = true;
		}
		em.close();
		return result;
	}

	@Override
	public T recuperar(Serializable id) {
		 Query consulta = EMF.getEMF().createEntityManager()
				 					  .createQuery("SELECT t FROM "+getPersistentClass().getSimpleName()
				 							  	  +" t WHERE t.id = :id");
		 consulta.setParameter("id", id);
		 T resultado = (T) consulta.getSingleResult();
		 return resultado;
	}
}
