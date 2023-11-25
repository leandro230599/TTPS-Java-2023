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
	private Usuarios usuario3;
	private UsuariosDAO userDAO;
	
	
	@BeforeEach
    void setUp() throws Exception {
        usuario = new Usuarios();
        usuario2 = new Usuarios();
        usuario3 = new Usuarios();
		userDAO = FactoryDAO.getUsuarioDAO();
		usuario = userDAO.recuperarPorEmailYPassword("asdasd@gmail.com", "123probando");
		usuario2 = userDAO.recuperarPorEmailYPassword("asd2@gmail.com", "AAAAAA");
    }
	
	
//	@Test
//	public void testRegistro() {
//		usuario = new Usuarios();
//		usuario2 = new Usuarios();
//		UsuariosDAO userDAO = FactoryDAO.getUsuarioDAO();
//		List<Usuarios> lista = new ArrayList<Usuarios>();
//		usuario = usuario.crearUsuario("user1", "leandro", "lopez", "asdasd@gmail.com", "123probando");
//		usuario2 = usuario2.crearUsuario("user2", "juan", "carlos", "asd2@gmail.com", "AAAAAA");
//		userDAO.persistir(usuario);
//		userDAO.persistir(usuario2);
//		lista = userDAO.recuperarTodos("username");
//		
//		usuario3 = usuario3.crearUsuario("user3", "pepe", "argento", "asd3@gmail.com", "REPITO");
//		userDAO.persistir(usuario3);
//		assertEquals(userDAO.recuperarPorEmailYPassword("asd3@gmail.com", "REPITO").getUsername(), "cuack");
//		
//	}
	
//	@Test
//	public void testExisteUsuario() {
//		usuario = new Usuarios();
//		UsuariosDAO userDAO = FactoryDAO.getUsuarioDAO();
//		usuario = userDAO.recuperarPorEmailYPassword("asd2@gmail.com", "AAAAAA");
//		assertEquals(usuario.getUsername(), "user2");
//	}
	
//	@Test
//	public void testAgregarAmigo() {
//		assertEquals(usuario.getAmigos().size(),0);
//		userDAO.agregarAmigo(usuario, usuario2);
//		assertEquals(usuario.getAmigos().size(),1);
//	}
	
//	@Test
//	public void testasd() {
//		Usuarios user3 = new Usuarios();
//		user3 = userDAO.recuperarPorEmailYPassword("leo@gmail.com", "123probando");
//	}
	
//	@Test
//	public void testEliminarAmigo() {
//		assertEquals(usuario.getAmigos().size(),1);
//		userDAO.eliminarAmigo(usuario, usuario2);
//		assertEquals(usuario.getAmigos().size(),0);
//	}
	
	@Test
	public void testEliminarUsuario() {
		Usuarios userAux = new Usuarios();
		userAux = userDAO.recuperar( 4L);
		assertTrue(userDAO.existe(4L));
		userDAO.borrar(userAux);
		assertFalse(userDAO.existe(4L));
		
	}
}
