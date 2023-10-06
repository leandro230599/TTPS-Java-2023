package clasesDAO;

import java.util.List;

import clasesDeObjetosDelSistema.Mensaje;

public interface MensajeDAO {
	public void guardar(Mensaje m);
	public List<Mensaje> cargar ();
}
