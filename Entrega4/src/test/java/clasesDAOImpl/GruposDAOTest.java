package clasesDAOImpl;
import static org.junit.jupiter.api.Assertions.*;
import java.util.*;
import org.junit.jupiter.api.*;

import clasesDAO.CategoriaDAO;
import clasesDAO.FactoryDAO;
import clasesDAO.GruposDAO;
import clasesDAO.UsuariosDAO;
import clasesModelo.Categoria;
import clasesModelo.Grupos;
import clasesModelo.Usuarios;

public class GruposDAOTest {
	private Grupos grupo;
	private Usuarios usuario;
	private Usuarios usuario2;
	private Usuarios usuario3;
	private Categoria categoria;
	private UsuariosDAO userDAO;
	private GruposDAO grupoDAO;
	private CategoriaDAO categoriaDAO;
	
	@BeforeEach
    void setUp() throws Exception {
        usuario = new Usuarios();
        usuario2 = new Usuarios();
        usuario3 = new Usuarios();
        categoria = new Categoria();
		userDAO = FactoryDAO.getUsuarioDAO();
		grupoDAO = FactoryDAO.getGrupoDAO();
		categoriaDAO = FactoryDAO.getCategoriaDAO();
		usuario = userDAO.recuperarPorEmailYPassword("leo@gmail.com", "123probando");
		usuario2 = userDAO.recuperarPorEmailYPassword("asd@gmail.com", "AAAAAA");
		usuario3 = userDAO.recuperarPorEmailYPassword("01k@gmail.com", "REPITO");
		categoria = categoriaDAO.recuperarPorNombreYTipo("Viaje", "grupo");
    }
	
	
//	@Test
//	public void testRegistro() {
//		grupo = new Grupos();
//		grupo = usuario.crearGrupo("Rumbo a MDQ", categoria);
//		System.out.println(grupo.getNombre());
//		System.out.println(grupo.getMiembros().size());
//		grupoDAO.crearGrupo(grupo, userDAO, usuario);
//		List<Grupos> lista = new ArrayList<Grupos>();
//		lista = grupoDAO.recuperarTodos("nombre");
//		assertEquals(lista.size(),1);
//	}
	
	@Test
	public void testAgregarAGrupo() {
		grupo = new Grupos();
		grupo = grupoDAO.recuperar((long) 3);
		assertTrue(grupoDAO.agregarAGrupo(userDAO, usuario, usuario2, grupo));
		assertFalse(grupoDAO.agregarAGrupo(userDAO, usuario, usuario3, grupo));
	}
	
//	@Test
//	public void testSalirseDeGrupo() {
//		grupo = new Grupos();
//		grupo = grupoDAO.recuperar((long) 3);
//		assertTrue(grupoDAO.salirseDeGrupo(userDAO, usuario2, grupo));
//	}
	
}