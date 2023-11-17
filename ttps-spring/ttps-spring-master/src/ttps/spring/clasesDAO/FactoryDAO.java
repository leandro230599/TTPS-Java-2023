package ttps.spring.clasesDAO;

import ttps.spring.clasesDAOImpl.CategoriaDAOHibernateJPA;
import ttps.spring.clasesDAOImpl.DeudaDAOHibernateJPA;
import ttps.spring.clasesDAOImpl.FormaDividirDAOHibernateJPA;
import ttps.spring.clasesDAOImpl.GastoDAOHibernateJPA;
import ttps.spring.clasesDAOImpl.GruposDAOHibernateJPA;
import ttps.spring.clasesDAOImpl.PagoDAOHibernateJPA;
import ttps.spring.clasesDAOImpl.UsuariosDAOHibernateJPA;

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
