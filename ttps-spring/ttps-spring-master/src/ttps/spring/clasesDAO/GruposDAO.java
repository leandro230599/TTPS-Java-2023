package ttps.spring.clasesDAO;

import java.util.Date;
import java.util.List;

import ttps.spring.model.Categoria;
import ttps.spring.model.Gasto;
import ttps.spring.model.Grupos;
import ttps.spring.model.Usuarios;

public interface GruposDAO extends GenericDAO<Grupos>{
	public void crearGrupo (String nombre, Long idCat, Long idUser);
	public void actualizarGrupo(Long idGrupo, String nombre, Long idCat);
	public void crearGastoGrupo(Long idCat, Date fecha, double monto, Long idUser, Long idFD, int fdV,
			Long idGrupoPersona);
	public void editarGastoGrupo(Long idCat, Date fecha, double monto, Long idUser, Long idFD, int fdV,
			Long idGrupoPersona, Long idGasto);
	public boolean agregarAGrupo(Usuarios user, Long idGrupo);
	public boolean salirseDeGrupo(Long idUser, Long idGrupo);
	public List<Gasto> obtenerGastosGrupo(Long idGrupo);
}
