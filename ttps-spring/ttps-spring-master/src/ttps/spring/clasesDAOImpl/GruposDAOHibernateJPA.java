package ttps.spring.clasesDAOImpl;

import org.springframework.stereotype.Repository;

import ttps.spring.clasesDAO.GruposDAO;
import ttps.spring.clasesDAO.UsuariosDAO;
import ttps.spring.model.Grupos;
import ttps.spring.model.Usuarios;

@Repository
public class GruposDAOHibernateJPA extends GenericDAOHibernateJPA<Grupos> implements GruposDAO{

	public GruposDAOHibernateJPA() {
		super(Grupos.class);
	}

	@Override
	public void crearGrupo(Grupos grupo, UsuariosDAO userDAO, Usuarios user) {
		persistir(grupo);
		userDAO.actualizar(user);
	}

}
