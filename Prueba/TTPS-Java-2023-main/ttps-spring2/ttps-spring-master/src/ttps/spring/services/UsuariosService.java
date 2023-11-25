package ttps.spring.services;

import ttps.spring.clasesDAO.UsuariosDAO;
import ttps.spring.model.Usuarios;

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
}
