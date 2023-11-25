package ttps.spring.clasesDAOImpl;

import java.util.Date;

import org.springframework.stereotype.Repository;

import ttps.spring.clasesDAO.CategoriaDAO;
import ttps.spring.clasesDAO.DeudaDAO;
import ttps.spring.clasesDAO.FactoryDAO;
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

	public GastoDAOHibernateJPA() {
		super(Gasto.class);
	}
	
	@Override
	public void crearGasto(Long idCat, Date fecha, double monto, Long idUser, String tipo, Long idFD, int fdV, Long idGrupoPersona) {
		CategoriaDAO catDAO = FactoryDAO.getCategoriaDAO();
		Categoria categoria = catDAO.recuperar(idCat);
		FormaDividirDAO fdDAO = FactoryDAO.getFormaDividirDAO();
		FormaDividir formaDividir = fdDAO.recuperar(idFD);
		UsuariosDAO userDAO = FactoryDAO.getUsuarioDAO();
		DeudaDAO deudaDAO = FactoryDAO.getDeudaDAO();
		Gasto gasto = new Gasto();
		if (tipo == "grupo") {
			GruposDAO grupoDAO = FactoryDAO.getGrupoDAO();
			Grupos grupo = grupoDAO.recuperar(idGrupoPersona);
			gasto.crearGasto(idUser, fecha, monto, categoria, tipo, formaDividir, fdV, null, grupo);
			grupo.getGastoGrupal().add(gasto);
			grupoDAO.actualizar(grupo);
			Usuarios user = userDAO.recuperar(idUser);
			deudaDAO.crearDeudaGrupo(grupo, idFD, fdV, monto, tipo, user);
		} else {
			Usuarios user = userDAO.recuperar(idGrupoPersona);
			Usuarios userRealizoGasto = userDAO.recuperar(idUser);
			gasto.crearGasto(idUser, fecha, monto, categoria, tipo, formaDividir, fdV, user, null);		
			user.getGastoPersonas().add(gasto);
			userDAO.actualizar(user);
			deudaDAO.crearDeudaPersona(user, idFD, fdV, monto, tipo, userRealizoGasto);
		}
		persistir(gasto);
		
	}

}
