package ttps.clasificados;

public class SitioClasificado {
	private String name;
	private String email;
	private int nroTel;
	
	public SitioClasificado(String name, String email, int nroTel) {
		super();
		this.name = name;
		this.email = email;
		this.nroTel = nroTel;
	}

	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}

	public int getNroTel() {
		return nroTel;
	}
	
	
}
