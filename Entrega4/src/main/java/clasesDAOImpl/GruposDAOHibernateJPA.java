package clasesDAOImpl;

import clasesDAO.GruposDAO;
import clasesModelo.Grupos;

public class GruposDAOHibernateJPA extends GenericDAOHibernateJPA<Grupos> implements GruposDAO{

	public GruposDAOHibernateJPA() {
		super(Grupos.class);
	}

}
