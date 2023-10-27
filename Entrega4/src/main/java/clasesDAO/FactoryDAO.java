package clasesDAO;

import clasesDAOImpl.UsuariosDAOHibernateJPA;

public class FactoryDAO {
	public static UsuariosDAO getUsuarioDAO() {
		return new UsuariosDAOHibernateJPA();
	}
}
