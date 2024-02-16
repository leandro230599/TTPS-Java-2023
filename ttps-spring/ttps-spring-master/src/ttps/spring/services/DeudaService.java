package ttps.spring.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ttps.spring.clasesDAO.CategoriaDAO;
import ttps.spring.clasesDAO.DeudaDAO;
import ttps.spring.clasesDAO.GruposDAO;
import ttps.spring.clasesDAO.UsuariosDAO;
import ttps.spring.model.Categoria;
import ttps.spring.model.Deuda;
import ttps.spring.model.Grupos;
import ttps.spring.model.Usuarios;

@Service
public class DeudaService {
	
		private DeudaDAO deudaDAO;
		
		@Autowired
		public DeudaService (DeudaDAO deuda) {
			this.deudaDAO = deuda;
		}
		
		public List<Deuda> getDeudas(Long idUser, Long idGrupoPersona, String tipo){
			return this.deudaDAO.getDeudaUser(idUser, idGrupoPersona, tipo);
		}
		
}
