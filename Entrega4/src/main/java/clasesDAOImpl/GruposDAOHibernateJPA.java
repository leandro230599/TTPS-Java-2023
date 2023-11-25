package clasesDAOImpl;

import clasesDAO.GruposDAO;
import clasesDAO.UsuariosDAO;
import clasesModelo.Grupos;
import clasesModelo.Usuarios;

public class GruposDAOHibernateJPA extends GenericDAOHibernateJPA<Grupos> implements GruposDAO{

	public GruposDAOHibernateJPA() {
		super(Grupos.class);
	}

	@Override
	public void crearGrupo(Grupos grupo, UsuariosDAO userDAO, Usuarios user) {
		persistir(grupo);
		userDAO.actualizar(user);
	}

	@Override
	public boolean agregarAGrupo(UsuariosDAO userDAO, Usuarios user1, Usuarios user2, Grupos grupo) {
		if (user1.agregarAGrupo(user2, grupo)) {
			userDAO.actualizar(user2);
			actualizar(grupo);
			return true;
		}
		return false;
	}

	@Override
	public boolean salirseDeGrupo(UsuariosDAO userDAO, Usuarios user, Grupos grupo) {
		if (user.salirseDeGrupo(grupo)) {
			userDAO.actualizar(user);
			actualizar(grupo);
			return true;
		}
		return false;
	}
}
