package clasesDAOImpl;

import clasesDAO.DeudaDAO;
import clasesModelo.Deuda;

public class DeudaDAOHibernateJPA extends GenericDAOHibernateJPA<Deuda> implements DeudaDAO{

	public DeudaDAOHibernateJPA() {
		super(Deuda.class);
	}

}
