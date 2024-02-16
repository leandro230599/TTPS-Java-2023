package ttps.spring.clasesDAO;

import java.util.List;

import ttps.spring.model.FormaDividir;

public interface FormaDividirDAO extends GenericDAO<FormaDividir>{
	public List<FormaDividir> getFormasDividir();
}
