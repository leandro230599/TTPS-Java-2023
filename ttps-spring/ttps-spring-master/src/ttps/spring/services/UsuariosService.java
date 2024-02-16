package ttps.spring.services;

import ttps.spring.clasesDAO.UsuariosDAO;
import ttps.spring.model.Usuarios;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UsuariosService {
	private UsuariosDAO userDAO;
	
	@Autowired
	public UsuariosService(UsuariosDAO user) {
		this.userDAO= user;
	}
	
	public Usuarios recuperarPorEmailYPassword(String email, String password) {
		return this.userDAO.recuperarPorEmailYPassword(email, password);
	}
	
	public void agregarAmigo(Usuarios user1, Usuarios user2) {
		this.userDAO.agregarAmigo(user1, user2);
	}

	public void eliminarAmigo(Usuarios user1, Usuarios user2) {
		this.userDAO.eliminarAmigo(user1, user2);
	}
	
	public Usuarios crearUsuario(String username, String first_name, String last_name, String email, String password) {
		return this.userDAO.crearUsuario(username, first_name, last_name, email, password);
	}
	
	public Usuarios recuperarPorId(Long id) {
		return this.userDAO.recuperar(id);
	}
	public void crearGastoPersona(Long idCat, Date fecha, double monto, Long idUser, Long idFD, int fdV,
			Long idGrupoPersona) {
		this.userDAO.crearGastoPersona(idCat, fecha, monto, idUser, idFD, fdV, idGrupoPersona);
	}
	public void editarGastoPersona(Long idCat, Date fecha, double monto, Long idUser, Long idFD, int fdV, Long idGrupoPersona, Long idGasto) {
		this.userDAO.editarGastoPersona(idCat, fecha, monto, idUser, idFD, fdV, idGrupoPersona, idGasto);
	}
	
	public Usuarios recuperarPorUsername (String username) {
		return this.userDAO.recuperarPorUsername(username);
	}
	
	public boolean userPerteneceAGrupo(Long idUser, Long idGrupo) {
		return this.userDAO.userPerteneceAGrupo(idUser, idGrupo);
	}
}
