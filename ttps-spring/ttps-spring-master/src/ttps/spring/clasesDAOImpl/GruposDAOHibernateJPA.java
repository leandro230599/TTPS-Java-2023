package ttps.spring.clasesDAOImpl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ttps.spring.clasesDAO.CategoriaDAO;
import ttps.spring.clasesDAO.GastoDAO;
import ttps.spring.clasesDAO.GruposDAO;
import ttps.spring.clasesDAO.UsuariosDAO;
import ttps.spring.model.Categoria;
import ttps.spring.model.Gasto;
import ttps.spring.model.Grupos;
import ttps.spring.model.Usuarios;

@Repository
public class GruposDAOHibernateJPA extends GenericDAOHibernateJPA<Grupos> implements GruposDAO{

	private UsuariosDAO userDAO;
	private CategoriaDAO catDAO;
	private GastoDAO gastoDAO;

	public GruposDAOHibernateJPA() {
		super(Grupos.class);
	}
	
	@Autowired
	public void setUserDAO(UsuariosDAO userDAO) {
		this.userDAO = userDAO;
	}

	@Autowired
	public void setCatDAO(CategoriaDAO catDAO) {
		this.catDAO = catDAO;
	}
	
	@Autowired
	public void setGastoDAO(GastoDAO gastoDAO) {
		this.gastoDAO = gastoDAO;
	}

	@Override
	public void crearGrupo(String nombre, Long idCat, Long idUser) {
		Usuarios user = userDAO.recuperar(idUser);
		Categoria categoria = catDAO.recuperar(idCat);
		Grupos grupo = user.crearGrupo(nombre, categoria);
 		persistir(grupo);
		userDAO.actualizar(user);
	}
	
	@Override
	public void actualizarGrupo(Long idGrupo, String nombre, Long idCat) {
		Categoria categoria = catDAO.recuperar(idCat);
		Grupos grupo = this.recuperar(idGrupo);
		grupo.editarInformacion(nombre, categoria);
		this.actualizar(grupo);
	}
	
	@Override
	public void crearGastoGrupo(Long idCat, Date fecha, double monto, Long idUser, Long idFD, int fdV,
			Long idGrupoPersona) {
		gastoDAO.crearGasto(idCat, fecha, monto, idUser, "grupo", idFD, fdV, idGrupoPersona);
	}
	
	@Override
	public void editarGastoGrupo(Long idCat, Date fecha, double monto, Long idUser, Long idFD, int fdV,
			Long idGrupoPersona, Long idGasto) {
		gastoDAO.editarGasto(idCat,fecha, monto, idUser, "grupo", idFD, fdV, idGrupoPersona, idGasto);
	}
	
	@Override
	public boolean agregarAGrupo(Usuarios user, Long idGrupo) {
		Grupos grupo = this.recuperar(idGrupo);
		if (user.agregarAGrupo(grupo)) {
			userDAO.actualizar(user);
			this.actualizar(grupo);
			return true;
		}			
		return false;
	}

	@Override
	public boolean salirseDeGrupo(Long idUser, Long idGrupo) {
		Usuarios user = userDAO.recuperar(idUser);
		Grupos grupo = this.recuperar(idGrupo);
		if (user.salirseDeGrupo(grupo)) {
			userDAO.actualizar(user);
			this.actualizar(grupo);
			return true;
		}
		return false;
	}
	
	@Override
	public List<Gasto> obtenerGastosGrupo(Long idGrupo){
		Grupos grupo = this.recuperar(idGrupo);
		return grupo.getGastoGrupal();
	}

	@Override
	public List<Grupos> getGruposUser(Long idUser) {
		Usuarios user = userDAO.recuperar(idUser);
		return user.getGrupos();
	}

}
