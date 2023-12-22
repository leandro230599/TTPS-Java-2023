package ttps.spring.clasesDAOImpl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ttps.spring.clasesDAO.CategoriaDAO;
import ttps.spring.clasesDAO.DeudaDAO;
import ttps.spring.clasesDAO.FormaDividirDAO;
import ttps.spring.clasesDAO.GastoDAO;
import ttps.spring.clasesDAO.GruposDAO;
import ttps.spring.clasesDAO.UsuariosDAO;
import ttps.spring.model.Categoria;
import ttps.spring.model.FormaDividir;
import ttps.spring.model.Gasto;
import ttps.spring.model.Grupos;
import ttps.spring.model.Usuarios;

@Repository
public class GastoDAOHibernateJPA extends GenericDAOHibernateJPA<Gasto> implements GastoDAO{

	private CategoriaDAO catDAO;
	private FormaDividirDAO fdDAO;
	private UsuariosDAO userDAO;
	private DeudaDAO deudaDAO;
	private GruposDAO grupoDAO;

	public GastoDAOHibernateJPA() {
		super(Gasto.class);
	}
	
	@Autowired
	public void setCatDAO(CategoriaDAO catDAO) {
		this.catDAO = catDAO;
	}

	@Autowired
	public void setFdDAO(FormaDividirDAO fdDAO) {
		this.fdDAO = fdDAO;
	}

	@Autowired
	public void setUserDAO(UsuariosDAO userDAO) {
		this.userDAO = userDAO;
	}

	@Autowired
	public void setDeudaDAO(DeudaDAO deudaDAO) {
		this.deudaDAO = deudaDAO;
	}

	@Autowired
	public void setGrupoDAO(GruposDAO grupoDAO) {
		this.grupoDAO = grupoDAO;
	}
	
	@Override
	public void crearGasto(Long idCat, Date fecha, double monto, Long idUser, String tipo, Long idFD, int fdV, Long idGrupoPersona) {
		Categoria categoria = catDAO.recuperar(idCat);
		FormaDividir formaDividir = fdDAO.recuperar(idFD);
		Gasto gasto = new Gasto();
		Usuarios userGasto = userDAO.recuperar(idUser);
		if (tipo == "grupo") {
			Grupos grupo = grupoDAO.recuperar(idGrupoPersona);
			Usuarios userNull = userDAO.recuperar(0L);
			gasto.crearGasto(userGasto, fecha, monto, categoria, tipo, formaDividir, fdV, userNull, grupo);
			this.persistir(gasto);
			grupo.getGastoGrupal().add(gasto);
			grupoDAO.actualizar(grupo);
			Usuarios user = userDAO.recuperar(idUser);
			deudaDAO.crearDeudaGrupo(grupo, idFD, fdV, monto, tipo, user, gasto);
		} else {
			Usuarios user = userDAO.recuperar(idGrupoPersona);
			Usuarios userRealizoGasto = userDAO.recuperar(idUser);
			Grupos grupoNull = grupoDAO.recuperar(0L);
			gasto.crearGasto(userGasto, fecha, monto, categoria, tipo, formaDividir, fdV, user, grupoNull);		
			this.persistir(gasto);
			user.getGastoPersonas().add(gasto);
			userDAO.actualizar(user);
			deudaDAO.crearDeudaPersona(user, idFD, fdV, monto, tipo, userRealizoGasto, gasto);
		}
	}

	@Override
	public void editarGasto(Long idCat, Date fecha, double monto, Long idUser, String tipo, Long idFD, int fdV,
			Long idGrupoPersona, Long idGasto) {
		Categoria categoria = catDAO.recuperar(idCat);
		FormaDividir formaDividir = fdDAO.recuperar(idFD);
		Gasto gasto = this.recuperar(idGasto);
		Usuarios userGasto = userDAO.recuperar(idUser);
		gasto.editarGasto(userGasto, fecha, monto, categoria, formaDividir, fdV);
		this.actualizar(gasto);
		deudaDAO.actualizarDeuda(idFD, fdV, monto, idGasto);		
	}

}
