package clasesDAOImpl;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EMF {
	private static final EntityManagerFactory em = Persistence.createEntityManagerFactory("miUP");

	public static EntityManagerFactory getEMF() {
		return em;
	}
}
