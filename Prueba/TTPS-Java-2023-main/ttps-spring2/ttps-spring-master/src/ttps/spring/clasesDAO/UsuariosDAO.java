package ttps.spring.clasesDAO;

import java.util.List;

import ttps.spring.model.Categoria;
import ttps.spring.model.Grupos;
import ttps.spring.model.Usuarios;

public interface UsuariosDAO extends GenericDAO<Usuarios>{
	public Usuarios recuperarPorEmailYPassword (String email, String password);
	public void agregarAmigo(Usuarios user1, Usuarios user2);
	public void eliminarAmigo(Usuarios user1, Usuarios user2);
	public Usuarios crearUsuario(String username, String first_name, String last_name, String email, String password);
}
