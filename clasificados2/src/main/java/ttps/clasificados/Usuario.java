package ttps.clasificados;

public class Usuario {
	private String username;
	private String password;
	private boolean esAdmin;
	
	public Usuario (String username, String password, boolean esAdmin) {
		this.username = username;
		this.password = password;
		this.esAdmin = esAdmin;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public boolean isEsAdmin() {
		return esAdmin;
	}
	
	
}
