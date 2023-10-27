package clasesDAO;

import clasesModelo.Categoria;

public interface CategoriaDAO extends GenericDAO<Categoria>{

	public Categoria recuperarPorNombreYTipo(String nombre, String tipo);
}
