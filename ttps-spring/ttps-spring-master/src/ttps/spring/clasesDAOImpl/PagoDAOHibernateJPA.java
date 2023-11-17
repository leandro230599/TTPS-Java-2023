package ttps.spring.clasesDAOImpl;

import org.springframework.stereotype.Repository;

import ttps.spring.clasesDAO.PagoDAO;
import ttps.spring.model.Pago;

@Repository
public class PagoDAOHibernateJPA extends GenericDAOHibernateJPA<Pago> implements PagoDAO{

	public PagoDAOHibernateJPA() {
		super(Pago.class);
	}

}
