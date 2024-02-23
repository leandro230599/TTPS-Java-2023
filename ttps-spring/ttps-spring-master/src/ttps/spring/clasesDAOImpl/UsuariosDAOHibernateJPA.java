package ttps.spring.clasesDAOImpl;

import ttps.spring.clasesDAO.GastoDAO;
import ttps.spring.clasesDAO.GruposDAO;
import ttps.spring.clasesDAO.UsuariosDAO;
import ttps.spring.model.Categoria;
import ttps.spring.model.Grupos;
import ttps.spring.model.Usuarios;

import jakarta.persistence.*;

import java.sql.SQLException;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UsuariosDAOHibernateJPA extends GenericDAOHibernateJPA<Usuarios> implements UsuariosDAO{
	
	private GastoDAO gastoDAO;
	private GruposDAO grupoDAO;

	public UsuariosDAOHibernateJPA(){
		super(Usuarios.class);
	}
	
	@Autowired
	public void setGastoDAO(GastoDAO gastoDAO) {
		this.gastoDAO = gastoDAO;
	}

	@Override
	public Usuarios recuperarPorEmailYPassword(String email, String password) {
		Usuarios resultado;
		Query consulta = this.getEntityManager()
				.createQuery("FROM Usuarios u WHERE u.email = :email AND u.password = :password");
		consulta.setParameter("email", email);
		consulta.setParameter("password", password);
		try {
			resultado = (Usuarios) consulta.getSingleResult();							
		} catch (NoResultException e) {
			resultado = null;
		}
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
		Usuarios userAux = this.recuperarPorEmail(user.getEmail());
		if (userAux !=null) {
			return null;
		}
		userAux = this.recuperarPorUsername(user.getUsername());
		if (userAux !=null) {
			return null;
		}
		this.persistir(user);
		return user;
	}

	@Override
	public void crearGastoPersona(Long idCat, Date fecha, double monto, Long idUser, Long idFD, int fdV,
			Long idGrupoPersona) {
		gastoDAO.crearGasto(idCat, fecha, monto, idUser, "persona", idFD, fdV, idGrupoPersona);
	}
	
	@Override
	public void editarGastoPersona(Long idCat, Date fecha, double monto, Long idUser, Long idFD, int fdV,
			Long idGrupoPersona, Long idGasto) {
		gastoDAO.editarGasto(idCat,fecha, monto, idUser, "persona", idFD, fdV, idGrupoPersona, idGasto);
	}
	
	@Override
	public Usuarios recuperarPorUsername(String username) {
		Usuarios resultado;
		Query consulta = this.getEntityManager()
				.createQuery("FROM Usuarios u WHERE u.username = :username");
		consulta.setParameter("username", username);
		try {
			resultado = (Usuarios) consulta.getSingleResult();							
		} catch (NoResultException e) {
			resultado = null;
		}
		return resultado;
	}
	
	@Override
	public Usuarios recuperarPorEmail(String email) {
		Usuarios resultado;
		Query consulta = this.getEntityManager()
				.createQuery("FROM Usuarios u WHERE u.email = :email");
		consulta.setParameter("email", email);
		try {
			resultado = (Usuarios) consulta.getSingleResult();							
		} catch (NoResultException e) {
			resultado = null;
		}
		return resultado;
	}

	@Override
	public boolean userPerteneceAGrupo(Long idUser, Long idGrupo) {
		Grupos grupo = this.grupoDAO.recuperar(idGrupo);
		Usuarios user = this.recuperar(idUser);
		return user.perteneceAGrupo(grupo);
	}

}
