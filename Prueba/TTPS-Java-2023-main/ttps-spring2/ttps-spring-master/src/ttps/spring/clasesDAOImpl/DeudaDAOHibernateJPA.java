package ttps.spring.clasesDAOImpl;

import org.springframework.stereotype.Repository;

import ttps.spring.clasesDAO.DeudaDAO;
import ttps.spring.model.Deuda;

@Repository
public class DeudaDAOHibernateJPA extends GenericDAOHibernateJPA<Deuda> implements DeudaDAO{

	public DeudaDAOHibernateJPA() {
		super(Deuda.class);
	}

}
