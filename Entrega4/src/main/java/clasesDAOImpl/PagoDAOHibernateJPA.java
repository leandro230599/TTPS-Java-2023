package clasesDAOImpl;

import clasesDAO.PagoDAO;
import clasesModelo.Pago;

public class PagoDAOHibernateJPA extends GenericDAOHibernateJPA<Pago> implements PagoDAO{

	public PagoDAOHibernateJPA() {
		super(Pago.class);
	}

}
