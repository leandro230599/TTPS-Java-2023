package clasesDAOImpl;

import clasesDAO.GastoDAO;
import clasesModelo.Gasto;

public class GastoDAOHibernateJPA extends GenericDAOHibernateJPA<Gasto> implements GastoDAO{

	public GastoDAOHibernateJPA() {
		super(Gasto.class);
	}

}
