package clasesDAOImpl;

import clasesDAO.FormaDividirDAO;
import clasesModelo.FormaDividir;

public class FormaDividirDAOHibernateJPA extends GenericDAOHibernateJPA<FormaDividir> implements FormaDividirDAO{

	public FormaDividirDAOHibernateJPA() {
		super(FormaDividir.class);
	}

}
