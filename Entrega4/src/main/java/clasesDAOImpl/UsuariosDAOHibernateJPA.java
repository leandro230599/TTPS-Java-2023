package clasesDAOImpl;

import clasesDAO.UsuariosDAO;
import clasesModelo.Usuarios;

import javax.persistence.*;

public class UsuariosDAOHibernateJPA extends GenericDAOHibernateJPA<Usuarios> implements UsuariosDAO{

	public UsuariosDAOHibernateJPA(){
		super(Usuarios.class);
	}

	@Override
	public Usuarios recuperarPorEmailYPassword(String email, String password) {
		Query consulta = EMF.getEMF().createEntityManager()
									 .createQuery("FROM Usuarios u WHERE u.email = :email AND u.password = :password");
		consulta.setParameter("email", email);
		consulta.setParameter("password", password);
		Usuarios resultado = (Usuarios) consulta.getSingleResult();
		return resultado;
	}

	@Override
	public void agregarAmigo(Usuarios user1, Usuarios user2) {
		user1.agregarAmigo(user2);
		actualizar(user1);
		actualizar(user2);
	}

	@Override
	public void eliminarAmigo(Usuarios user1, Usuarios user2) {
		EntityManager em= EMF.getEMF().createEntityManager();
		EntityTransaction etx= em.getTransaction();
		user1.eliminarAmigo(user2);
		etx.begin();
		String sql = "DELETE FROM Amigos WHERE (usuario_id = :usuario_id AND amigo_id = :amigo_id) OR"
											  + "(usuario_id = :amigo_id AND amigo_id = :usuario_id)";
		Query query = em.createNativeQuery(sql);
		query.setParameter("usuario_id", user1.getId());
		query.setParameter("amigo_id", user2.getId());
		query.executeUpdate();

		etx.commit();
		em.close();
		actualizar(user1);
		actualizar(user2);
	}

}
