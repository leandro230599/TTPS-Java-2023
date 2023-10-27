package clasesDAOImpl;

import clasesDAO.CategoriaDAO;
import clasesModelo.Categoria;

public class CategoriaDAOHibernateJPA extends GenericDAOHibernateJPA<Categoria> implements CategoriaDAO{

	public CategoriaDAOHibernateJPA() {
		super(Categoria.class);
	}
}
