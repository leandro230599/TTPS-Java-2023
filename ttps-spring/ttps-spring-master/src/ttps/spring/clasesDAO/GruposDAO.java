package ttps.spring.clasesDAO;

import ttps.spring.model.Grupos;
import ttps.spring.model.Usuarios;

public interface GruposDAO extends GenericDAO<Grupos>{
	public void crearGrupo (Grupos grupo, UsuariosDAO userDAO, Usuarios user);
}
