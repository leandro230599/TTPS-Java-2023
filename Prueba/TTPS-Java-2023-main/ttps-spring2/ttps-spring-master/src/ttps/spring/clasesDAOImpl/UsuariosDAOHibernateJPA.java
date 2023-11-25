package ttps.spring.clasesDAOImpl;

import ttps.spring.clasesDAO.UsuariosDAO;
import ttps.spring.model.Categoria;
import ttps.spring.model.Grupos;
import ttps.spring.model.Usuarios;

import jakarta.persistence.*;

import org.springframework.stereotype.Repository;

@Repository
public class UsuariosDAOHibernateJPA extends GenericDAOHibernateJPA<Usuarios> implements UsuariosDAO{

	public UsuariosDAOHibernateJPA(){
		super(Usuarios.class);
	}

	@Override
	public Usuarios recuperarPorEmailYPassword(String email, String password) {
		Query consulta = this.getEntityManager()
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
		user1.eliminarAmigo(user2);
		String sql = "DELETE FROM Amigos WHERE (usuario_id = :usuario_id AND amigo_id = :amigo_id) OR"
											  + "(usuario_id = :amigo_id AND amigo_id = :usuario_id)";
		Query query = this.getEntityManager().createNativeQuery(sql);
		query.setParameter("usuario_id", user1.getId());
		query.setParameter("amigo_id", user2.getId());
		query.executeUpdate();
		actualizar(user1);
		actualizar(user2);
		this.flushAndClear();
	}
	
	@Override
	public Usuarios crearUsuario(String username, String first_name, String last_name, String email, String password) {
		Usuarios user = new Usuarios();
		user.crearUsuario(username, first_name, last_name, email, password);
		this.persistir(user);
		return user;
	}

}
