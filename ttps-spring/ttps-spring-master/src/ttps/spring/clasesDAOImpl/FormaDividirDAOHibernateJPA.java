package ttps.spring.clasesDAOImpl;

import java.util.List;

import org.springframework.stereotype.Repository;

import jakarta.persistence.Query;
import ttps.spring.clasesDAO.FormaDividirDAO;
import ttps.spring.model.Categoria;
import ttps.spring.model.FormaDividir;

@Repository
public class FormaDividirDAOHibernateJPA extends GenericDAOHibernateJPA<FormaDividir> implements FormaDividirDAO{

	public FormaDividirDAOHibernateJPA() {
		super(FormaDividir.class);
	}

	@Override
	public List<FormaDividir> getFormasDividir() {
		Query consulta = this.getEntityManager()
				.createQuery("FROM FormaDividir");
		List<FormaDividir> resultado = (List<FormaDividir>) consulta.getResultList();
		return resultado;
	}

}
