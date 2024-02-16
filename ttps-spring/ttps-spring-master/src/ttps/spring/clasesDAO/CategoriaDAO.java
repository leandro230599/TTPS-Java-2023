package ttps.spring.clasesDAO;

import java.util.List;

import ttps.spring.model.Categoria;

public interface CategoriaDAO extends GenericDAO<Categoria>{

	public Categoria recuperarPorNombreYTipo(String nombre, String tipo);
	public List<Categoria> getCategorias(String tipo);	
}
