package ttps.spring.clasesDAO;
import ttps.spring.model.Deuda;
import ttps.spring.model.Grupos;
import ttps.spring.model.Usuarios;


public interface DeudaDAO extends GenericDAO<Deuda> {

	public void crearDeudaGrupo(Grupos grupo,Long idFD, int fdV, double monto, String tipo, Usuarios userRealizoGasto);
	public void crearDeudaPersona(Usuarios user,Long idFD, int fdV, double monto, String tipo, Usuarios userRealizoGasto);
}
