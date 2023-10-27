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

}
