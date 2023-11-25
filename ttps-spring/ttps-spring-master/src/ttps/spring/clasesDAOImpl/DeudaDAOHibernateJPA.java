package ttps.spring.clasesDAOImpl;

import org.springframework.stereotype.Repository;

import ttps.spring.clasesDAO.DeudaDAO;
import ttps.spring.clasesDAO.FactoryDAO;
import ttps.spring.clasesDAO.UsuariosDAO;
import ttps.spring.model.Deuda;
import ttps.spring.model.Grupos;
import ttps.spring.model.Usuarios;

@Repository
public class DeudaDAOHibernateJPA extends GenericDAOHibernateJPA<Deuda> implements DeudaDAO{

	public DeudaDAOHibernateJPA() {
		super(Deuda.class);
	}
	
	@Override
	public void crearDeudaGrupo(Grupos grupo,Long idFD, int fdV, double monto, String tipo, Usuarios userRealizoGasto) {
		UsuariosDAO userDAO = FactoryDAO.getUsuarioDAO();
		for (Usuarios user : grupo.getMiembros()) {
			switch (idFD.intValue()) {
				case 1: {
					Deuda deuda = new Deuda();
					double montoDividido = monto/grupo.getMiembros().size();
					deuda.crearDeuda(grupo.getId(), montoDividido, tipo, userRealizoGasto);
					user.getDeudas().add(deuda);
					persistir(deuda);
					userDAO.actualizar(user);
				}
				case 2:{
					Deuda deuda = new Deuda();
					deuda.crearDeuda(grupo.getId(), fdV, tipo, userRealizoGasto);
					user.getDeudas().add(deuda);
					persistir(deuda);
					userDAO.actualizar(user);
				}
				case 3:{
					Deuda deuda = new Deuda();
					double montoPorcentaje = monto*fdV/100;
					deuda.crearDeuda(grupo.getId(), montoPorcentaje, tipo, userRealizoGasto);
					user.getDeudas().add(deuda);
					persistir(deuda);
					userDAO.actualizar(user);
				}
				default:
					throw new IllegalArgumentException("No era esperado el valor: " + idFD);
			}
		}
	}
	
	@Override
	public void crearDeudaPersona(Usuarios user,Long idFD, int fdV, double monto, String tipo, Usuarios userRealizoGasto) {
		UsuariosDAO userDAO = FactoryDAO.getUsuarioDAO();
		switch (idFD.intValue()) {
		case 1: {
			Deuda deuda = new Deuda();
			double montoDividido = monto/2;
			deuda.crearDeuda(user.getId(), montoDividido, tipo, userRealizoGasto);
			user.getDeudas().add(deuda);
			userRealizoGasto.getDeudas().add(deuda);
			persistir(deuda);
			userDAO.actualizar(user);
			userDAO.actualizar(userRealizoGasto);
		}
		case 2:{
			Deuda deuda = new Deuda();
			deuda.crearDeuda(user.getId(), fdV, tipo, userRealizoGasto);
			user.getDeudas().add(deuda);
			userRealizoGasto.getDeudas().add(deuda);
			persistir(deuda);
			userDAO.actualizar(user);
			userDAO.actualizar(userRealizoGasto);
		}
		case 3:{
			Deuda deuda = new Deuda();
			double montoPorcentaje = monto*fdV/100;
			deuda.crearDeuda(user.getId(), montoPorcentaje, tipo, userRealizoGasto);
			user.getDeudas().add(deuda);
			userRealizoGasto.getDeudas().add(deuda);
			persistir(deuda);
			userDAO.actualizar(user);
			userDAO.actualizar(userRealizoGasto);
		}
		default:
			throw new IllegalArgumentException("No era esperado el valor: " + idFD);
	}
	}

}
