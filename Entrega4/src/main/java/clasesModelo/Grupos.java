package clasesModelo;

import java.util.List;

public class Grupos {
	private Long id;
	private String nombre;
	private Categoria categoria;
	private List<Usuarios> miembros;
	private List<Gasto> gastoGrupal;
	
	public List<Usuarios> getMiembros() {
		return miembros;
	}
	public List<Gasto> getGastoGrupal() {
		return gastoGrupal;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Categoria getCategoria() {
		return categoria;
	}
	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	public void invitarAGrupo(String username) {}
	public void agregarAGrupo(String username) {}
	public void editarInformacion(String nombre, Categoria categoria) {}
	public void crearGasto(Gasto gasto) {}
}
