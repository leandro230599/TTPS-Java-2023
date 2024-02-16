package ttps.spring.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ttps.spring.clasesDAO.FormaDividirDAO;
import ttps.spring.model.Categoria;
import ttps.spring.model.FormaDividir;

@Service
public class FormaDividirService {
	private FormaDividirDAO forDAO;
	
	@Autowired
	public FormaDividirService (FormaDividirDAO forD) {
		this.forDAO = forD;
	}
	
	public FormaDividir recuperarPorId (Long id) {
		return this.forDAO.recuperar(id);
	}
	
	public List<FormaDividir> getFormasDividir(){
		return this.forDAO.getFormasDividir();
	}

}
