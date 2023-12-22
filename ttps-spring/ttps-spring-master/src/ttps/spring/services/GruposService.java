package ttps.spring.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ttps.spring.clasesDAO.GruposDAO;
import ttps.spring.clasesDAO.UsuariosDAO;
import ttps.spring.model.Categoria;
import ttps.spring.model.Gasto;
import ttps.spring.model.Grupos;
import ttps.spring.model.Usuarios;

@Service
public class GruposService {
	private GruposDAO grupoDAO;
	
	@Autowired
	public GruposService (GruposDAO grupo) {
		this.grupoDAO = grupo;
	}
	
	public void crearGrupo(String nombre, Long idCat, Long idUser) {
		this.grupoDAO.crearGrupo(nombre, idCat, idUser);
	}
	public void actualizarGrupo (Long idGrupo, String nombre, Long idCat) {
		this.grupoDAO.actualizarGrupo(idGrupo, nombre, idCat);
	}
	public void crearGastoGrupo(Long idCat, Date fecha, double monto, Long idUser, Long idFD, int fdV,
			Long idGrupoPersona) {
		this.grupoDAO.crearGastoGrupo(idCat, fecha, monto, idUser, idFD, fdV, idGrupoPersona);
	}
	public void editarGastoGrupo(Long idCat, Date fecha, double monto, Long idUser, Long idFD, int fdV, Long idGrupoPersona, Long idGasto) {
		this.grupoDAO.editarGastoGrupo(idCat, fecha, monto, idUser, idFD, fdV, idGrupoPersona, idGasto);
	}
	public boolean agregarAGrupo(Usuarios user, Long idGrupo) {
		return this.grupoDAO.agregarAGrupo(user, idGrupo) ? true : false;
	}
	public boolean salirseDeGrupo(Long idUser, Long idGrupo) {
		return this.grupoDAO.salirseDeGrupo(idUser, idGrupo) ? true : false;
	}
	public List<Gasto> listarGastosGrupo(Long idGrupo){
		return this.grupoDAO.obtenerGastosGrupo(idGrupo);
	}
	public Grupos recuperarPorId(Long idGrupo) {
		return this.grupoDAO.recuperar(idGrupo);
	}
}
