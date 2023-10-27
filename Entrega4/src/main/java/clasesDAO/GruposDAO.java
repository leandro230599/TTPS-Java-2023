package clasesDAO;

import clasesModelo.Grupos;
import clasesModelo.Usuarios;

public interface GruposDAO extends GenericDAO<Grupos>{
	public void crearGrupo (Grupos grupo, UsuariosDAO userDAO, Usuarios user);
}
