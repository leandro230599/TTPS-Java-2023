package objects;

import java.util.List;

public class Usuarios {
	private Long id;
	private String username;
	private String first_name;
	private String last_name;
	private String email;
	private String password;
	private List<Usuarios> amigos;
	private List<Usuarios> personasQueCompartiGrupo;
	private List<Usuarios> solicitudesAmistad;
	private List<Gasto> gastoPersonas;
	private List<Grupos> grupos;
	private List<Deuda> deudas;
	private List<Pago> pagos;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getFirst_name() {
		return first_name;
	}
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}
	public String getLast_name() {
		return last_name;
	}
	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public List<Usuarios> getAmigos() {
		return amigos;
	}
	public List<Usuarios> getPersonasQueCompartiGrupo() {
		return personasQueCompartiGrupo;
	}
	public List<Usuarios> getSolicitudesAmistad() {
		return solicitudesAmistad;
	}
	public List<Gasto> getGastoPersonas() {
		return gastoPersonas;
	}
	public List<Grupos> getGrupos() {
		return grupos;
	}
	public List<Deuda> getDeudas() {
		return deudas;
	}
	public List<Pago> getPagos() {
		return pagos;
	}
	public void agregarAmigo(String username) {}
	public void crearGrupo(String nombre, Categoria categoria) {}
	public void crearGastoPersona(Gasto gasto) {}
	public void crearPago(Pago pago) {}

}
