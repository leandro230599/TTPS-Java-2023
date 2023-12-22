package ttps.spring.clasesDAO;

import java.util.Date;

import ttps.spring.model.Gasto;

public interface GastoDAO extends GenericDAO<Gasto>{
	public void crearGasto(Long idCat, Date fecha, double monto, Long idUser, String tipo, Long idFD, int fdV, Long idGrupoPersona);
	public void editarGasto(Long idCat, Date fecha, double monto, Long idUser, String tipo, Long idFD, int fdV, Long idGrupoPersona, Long idGasto);
}
