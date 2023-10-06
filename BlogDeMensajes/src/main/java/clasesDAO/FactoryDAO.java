package clasesDAO;

import clasesDAOImplJDBC.UsuarioDAOjdbc;
import clasesDAOImplJDBC.MensajeDAOjdbc;

public class FactoryDAO {
	public static UsuarioDAO getUsuarioDAO() {
		return new UsuarioDAOjdbc();
	}
	public static MensajeDAO getMensajeDAO() {
		return new MensajeDAOjdbc();
	}
}
