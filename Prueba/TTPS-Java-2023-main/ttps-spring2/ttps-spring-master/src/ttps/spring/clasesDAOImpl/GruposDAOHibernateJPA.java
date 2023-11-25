package ttps.spring.clasesDAOImpl;

import org.springframework.stereotype.Repository;

import ttps.spring.clasesDAO.CategoriaDAO;
import ttps.spring.clasesDAO.FactoryDAO;
import ttps.spring.clasesDAO.GruposDAO;
import ttps.spring.clasesDAO.UsuariosDAO;
import ttps.spring.model.Categoria;
import ttps.spring.model.Grupos;
import ttps.spring.model.Usuarios;

@Repository
public class GruposDAOHibernateJPA extends GenericDAOHibernateJPA<Grupos> implements GruposDAO{

	public GruposDAOHibernateJPA() {
		super(Grupos.class);
	}

	@Override
	public void crearGrupo(String nombre, Long idCat, Long idUser) {
		UsuariosDAO userDAO = FactoryDAO.getUsuarioDAO();
		Usuarios user = userDAO.recuperar(idUser);
		CategoriaDAO catDAO = FactoryDAO.getCategoriaDAO();
		Categoria categoria = catDAO.recuperar(idCat);
		Grupos grupo = user.crearGrupo(nombre, categoria);
 		persistir(grupo);
		userDAO.actualizar(user);
	}
	
	@Override
	public void actualizarGrupo(Long idGrupo, String nombre, Long idCat) {
		CategoriaDAO catDAO = FactoryDAO.getCategoriaDAO();
		Categoria categoria = catDAO.recuperar(idCat);
		GruposDAO grupoDAO = FactoryDAO.getGrupoDAO();
		Grupos grupo = grupoDAO.recuperar(idGrupo);
		grupo.editarInformacion(nombre, categoria);
		actualizar(grupo);
	}
	
	

}
