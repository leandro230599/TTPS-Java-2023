package ttps.spring.clasesDAO;

import ttps.spring.model.Categoria;
import ttps.spring.model.Grupos;
import ttps.spring.model.Usuarios;

public interface GruposDAO extends GenericDAO<Grupos>{
	public void crearGrupo (String nombre, Long idCat, Long idUser);
	public void actualizarGrupo(Long idGrupo, String nombre, Long idCat);
}
