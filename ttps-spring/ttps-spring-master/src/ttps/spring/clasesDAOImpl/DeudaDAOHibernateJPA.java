package ttps.spring.clasesDAOImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import jakarta.persistence.NoResultException;
import jakarta.persistence.Query;
import ttps.spring.clasesDAO.DeudaDAO;
import ttps.spring.clasesDAO.UsuariosDAO;
import ttps.spring.model.Deuda;
import ttps.spring.model.Gasto;
import ttps.spring.model.Grupos;
import ttps.spring.model.Usuarios;

@Repository
public class DeudaDAOHibernateJPA extends GenericDAOHibernateJPA<Deuda> implements DeudaDAO{
	
	private UsuariosDAO userDAO;

	public DeudaDAOHibernateJPA() {
		super(Deuda.class);
	}
	
	@Autowired
	public void setUserDAO(UsuariosDAO userDAO) {
		this.userDAO = userDAO;
	}
	
	@Override
	public void crearDeudaGrupo(Grupos grupo,Long idFD, int fdV, double monto, String tipo, Usuarios userRealizoGasto, Gasto gasto) {
		for (Usuarios user : grupo.getMiembros()) {
			switch (Integer.valueOf(idFD.intValue())) {
				case 1: {
					Deuda deuda = new Deuda();
					double montoDividido = monto/grupo.getMiembros().size();
					deuda.crearDeuda(grupo.getId(), montoDividido, tipo, user, gasto);
					user.getDeudas().add(deuda);
					persistir(deuda);
					userDAO.actualizar(user);
					break;
				}
				case 2:{
					Deuda deuda = new Deuda();
					deuda.crearDeuda(grupo.getId(), fdV, tipo, user, gasto);
					user.getDeudas().add(deuda);
					persistir(deuda);
					userDAO.actualizar(user);
					break;
				}
				case 3:{
					Deuda deuda = new Deuda();
					double montoPorcentaje = monto*fdV/100;
					deuda.crearDeuda(grupo.getId(), montoPorcentaje, tipo, user, gasto);
					user.getDeudas().add(deuda);
					persistir(deuda);
					userDAO.actualizar(user);
					break;
				}
				default:
					throw new IllegalArgumentException("No era esperado el valor: " + idFD);
			}
		}
	}
	
	@Override
	public void crearDeudaPersona(Usuarios user,Long idFD, int fdV, double monto, String tipo, Usuarios userRealizoGasto, Gasto gasto) {
		switch (idFD.intValue()) {
		case 1: {
			Deuda deuda = new Deuda();
			double montoDividido = monto/2;
			deuda.crearDeuda(user.getId(), montoDividido, tipo, userRealizoGasto, gasto);
			user.getDeudas().add(deuda);
			userRealizoGasto.getDeudas().add(deuda);
			persistir(deuda);
			userDAO.actualizar(user);
			userDAO.actualizar(userRealizoGasto);
		}
		case 2:{
			Deuda deuda = new Deuda();
			deuda.crearDeuda(user.getId(), fdV, tipo, userRealizoGasto, gasto);
			user.getDeudas().add(deuda);
			userRealizoGasto.getDeudas().add(deuda);
			persistir(deuda);
			userDAO.actualizar(user);
			userDAO.actualizar(userRealizoGasto);
		}
		case 3:{
			Deuda deuda = new Deuda();
			double montoPorcentaje = monto*fdV/100;
			deuda.crearDeuda(user.getId(), montoPorcentaje, tipo, userRealizoGasto, gasto);
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

	@Override
	public void actualizarDeuda(Long idFD, int fdV, double monto, Long idGasto) {
		List<Deuda> deudas;
		Query consulta = this.getEntityManager()
				 .createQuery("SELECT d FROM Deuda d JOIN d.gasto g WHERE g.id = :gasto", Deuda.class);
		consulta.setParameter("gasto", idGasto);
		deudas = (List<Deuda>) consulta.getResultList();				
		for (Deuda deuda : deudas) {
			switch (Integer.valueOf(idFD.intValue())) {
				case 1: {
					double montoDividido = monto/deudas.size();
					deuda.actualizarDeuda(montoDividido);
					this.actualizar(deuda);
					break;
				}
				case 2:{
					deuda.actualizarDeuda(fdV);
					this.actualizar(deuda);
					break;
				}
				case 3:{
					double montoPorcentaje = monto*fdV/100;
					deuda.actualizarDeuda(montoPorcentaje);
					this.actualizar(deuda);
					break;
				}
				default:
					throw new IllegalArgumentException("No era esperado el valor: " + idFD);
			}
		}
	}
	
}
