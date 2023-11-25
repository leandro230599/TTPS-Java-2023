package ttps.spring.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ttps.spring.clasesDAO.CategoriaDAO;
import ttps.spring.clasesDAO.GruposDAO;
import ttps.spring.clasesDAO.UsuariosDAO;
import ttps.spring.model.Categoria;
import ttps.spring.model.Grupos;
import ttps.spring.model.Usuarios;

@Service
public class CategoriaService {
	
		private CategoriaDAO catDAO;
		
		@Autowired
		public CategoriaService (CategoriaDAO cat) {
			this.catDAO = cat;
		}
		
		public Categoria recuperarPorNombreYTipo(String nombre, String tipo) {
			return this.catDAO.recuperarPorNombreYTipo(nombre, tipo);
		}
		
		public Categoria recuperarPorId (Long id) {
			return this.catDAO.recuperar(id);
		}
		
}
