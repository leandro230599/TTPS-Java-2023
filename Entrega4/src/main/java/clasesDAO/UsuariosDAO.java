package clasesDAO;

import java.util.List;

import clasesModelo.Usuarios;

public interface UsuariosDAO extends GenericDAO<Usuarios>{
	public Usuarios recuperarPorEmailYPassword (String email, String password);
	public void agregarAmigo(Usuarios user1, Usuarios user2);
	public void eliminarAmigo(Usuarios user1, Usuarios user2);
}
