package clasesDAO;

import clasesDAOImpl.CategoriaDAOHibernateJPA;
import clasesDAOImpl.DeudaDAOHibernateJPA;
import clasesDAOImpl.FormaDividirDAOHibernateJPA;
import clasesDAOImpl.GastoDAOHibernateJPA;
import clasesDAOImpl.GruposDAOHibernateJPA;
import clasesDAOImpl.PagoDAOHibernateJPA;
import clasesDAOImpl.UsuariosDAOHibernateJPA;

public class FactoryDAO {
	public static UsuariosDAO getUsuarioDAO() {
		return new UsuariosDAOHibernateJPA();
	}
	public static GruposDAO getGrupoDAO() {
		return new GruposDAOHibernateJPA();
	}
	public static CategoriaDAO getCategoriaDAO() {
		return new CategoriaDAOHibernateJPA();
	}
	public static DeudaDAO getDeudaDAO() {
		return new DeudaDAOHibernateJPA();
	}
	public static FormaDividirDAO getFormaDividirDAO() {
		return new FormaDividirDAOHibernateJPA();
	}
	public static GastoDAO getGastoDAO() {
		return new GastoDAOHibernateJPA();
	}
	public static PagoDAO getPagoDAO() {
		return new PagoDAOHibernateJPA();
	}
}
