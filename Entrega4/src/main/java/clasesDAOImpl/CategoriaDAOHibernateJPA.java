package clasesDAOImpl;

import javax.persistence.Query;

import clasesDAO.CategoriaDAO;
import clasesModelo.Categoria;
import clasesModelo.Usuarios;

public class CategoriaDAOHibernateJPA extends GenericDAOHibernateJPA<Categoria> implements CategoriaDAO{

	public CategoriaDAOHibernateJPA() {
		super(Categoria.class);
	}

	@Override
	public Categoria recuperarPorNombreYTipo(String nombre, String tipo) {
		Query consulta = EMF.getEMF().createEntityManager()
									 .createQuery("FROM Categoria c WHERE c.nombre = :nombre AND c.tipo = :tipo");
		consulta.setParameter("nombre", nombre);
		consulta.setParameter("tipo", tipo);
		Categoria resultado = (Categoria) consulta.getSingleResult();
		return resultado;
	}
}
