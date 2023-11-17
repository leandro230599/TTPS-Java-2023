package ttps.spring.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ttps.spring.clasesDAO.GruposDAO;
import ttps.spring.clasesDAO.UsuariosDAO;
import ttps.spring.model.Grupos;
import ttps.spring.model.Usuarios;

@Service
public class GruposService {
	private GruposDAO grupoDAO;
	
	@Autowired
	public GruposService (GruposDAO grupo) {
		this.grupoDAO = grupo;
	}
	
	public void crearGrupo(Grupos grupo, UsuariosDAO userDAO, Usuarios user) {
		this.grupoDAO.crearGrupo(grupo, userDAO, user);
	}
}
