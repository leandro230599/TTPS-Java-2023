package ttps.spring.clasesDAO;

import ttps.spring.model.Categoria;

public interface CategoriaDAO extends GenericDAO<Categoria>{

	public Categoria recuperarPorNombreYTipo(String nombre, String tipo);
}
