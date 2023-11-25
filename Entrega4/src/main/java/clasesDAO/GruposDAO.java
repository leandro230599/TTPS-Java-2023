package clasesDAO;

import clasesModelo.Grupos;
import clasesModelo.Usuarios;

public interface GruposDAO extends GenericDAO<Grupos>{
	public void crearGrupo (Grupos grupo, UsuariosDAO userDAO, Usuarios user);
	public boolean agregarAGrupo(UsuariosDAO userDAO, Usuarios user1, Usuarios user2, Grupos grupo);
	public boolean salirseDeGrupo(UsuariosDAO userDAO, Usuarios user, Grupos grupo);
}
