package ttps.spring.clasesDAOImpl;

import jakarta.persistence.Query;

import java.util.List;

import org.springframework.stereotype.Repository;

import ttps.spring.clasesDAO.CategoriaDAO;
import ttps.spring.model.Categoria;
import ttps.spring.model.Usuarios;

@Repository
public class CategoriaDAOHibernateJPA extends GenericDAOHibernateJPA<Categoria> implements CategoriaDAO{

	public CategoriaDAOHibernateJPA() {
		super(Categoria.class);
	}

	@Override
	public Categoria recuperarPorNombreYTipo(String nombre, String tipo) {
		Query consulta = this.getEntityManager()
									 .createQuery("FROM Categoria c WHERE c.nombre = :nombre AND c.tipo = :tipo");
		consulta.setParameter("nombre", nombre);
		consulta.setParameter("tipo", tipo);
		Categoria resultado = (Categoria) consulta.getSingleResult();
		return resultado;
	}

	@Override
	public List<Categoria> getCategorias(String tipo) {
		Query consulta = this.getEntityManager()
									.createQuery("FROM Categoria c WHERE c.tipo = :tipo");
		consulta.setParameter("tipo", tipo);
		List<Categoria> resultado = (List<Categoria>) consulta.getResultList();
		return resultado;
	}
}
