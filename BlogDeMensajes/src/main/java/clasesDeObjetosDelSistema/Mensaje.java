package clasesDeObjetosDelSistema;

public class Mensaje {
	private String texto;
	private String email;
	
	public Mensaje(String texto, String email) {
		super();
		this.texto = texto;
		this.email = email;
	}

	public String getTexto() {
		return texto;
	}

	public String getEmail() {
		return email;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	
	
}
