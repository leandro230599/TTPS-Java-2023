package clasesDAOImpl;

import static org.junit.jupiter.api.Assertions.*;

import java.util.*;

import org.junit.jupiter.api.*;

import clasesDAO.FactoryDAO;
import clasesDAO.UsuariosDAO;
import clasesModelo.Usuarios;

public class UsuariosDAOTest {
	private Usuarios usuario;
	private Usuarios usuario2;
	private UsuariosDAO userDAO;
	
	
	@BeforeEach
    void setUp() throws Exception {
        usuario = new Usuarios();
        usuario2 = new Usuarios();
		userDAO = FactoryDAO.getUsuarioDAO();
		usuario = userDAO.recuperarPorEmailYPassword("leo@gmail.com", "123probando");
		usuario2 = userDAO.recuperarPorEmailYPassword("asd@gmail.com", "123probando");
    }
	
	
	/*@Test
	public void testRegistro() {
		usuario = new Usuarios();
		UsuariosDAO userDAO = FactoryDAO.getUsuarioDAO();
		List<Usuarios> lista = new ArrayList<Usuarios>();
		usuario = usuario.crearUsuario("lezen", "leandro", "lopez", "leo@gmail.com", "123probando");
		userDAO.persistir(usuario);
		lista = userDAO.recuperarTodos("username");
		assertEquals(lista.size(),6);
		
	}*/
	
	/*@Test
	public void testExisteUsuario() {
		usuario = new Usuarios();
		UsuariosDAO userDAO = FactoryDAO.getUsuarioDAO();
		usuario = userDAO.recuperarPorEmailYPassword("leo@gmail.com", "123probando");
		assertEquals(usuario.getUsername(), "lezen");
	}*/
	
//	@Test
//	public void testAgregarAmigo() {
//		assertEquals(usuario.getAmigos().size(),0);
//		userDAO.agregarAmigo(usuario, usuario2);
//		assertEquals(usuario.getAmigos().size(),1);
//	}
	
	@Test
	public void testasd() {
		Usuarios user3 = new Usuarios();
		user3 = userDAO.recuperarPorEmailYPassword("leo@gmail.com", "123probando");
	}
//	@Test
//	public void testEliminarAmigo() {
//		assertEquals(usuario.getAmigos().size(),1);
//		userDAO.eliminarAmigo(usuario, usuario2);
//		assertEquals(usuario.getAmigos().size(),0);
//	}
}
