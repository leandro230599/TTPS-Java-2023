package ttps.spring.clasesDAOImpl;

import org.springframework.stereotype.Repository;

import ttps.spring.clasesDAO.FormaDividirDAO;
import ttps.spring.model.FormaDividir;

@Repository
public class FormaDividirDAOHibernateJPA extends GenericDAOHibernateJPA<FormaDividir> implements FormaDividirDAO{

	public FormaDividirDAOHibernateJPA() {
		super(FormaDividir.class);
	}

}
