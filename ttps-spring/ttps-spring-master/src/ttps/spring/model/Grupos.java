package ttps.spring.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;

@Entity
@Table(name="Grupos")
public class Grupos {
	
	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="grupo_id")
	private Long id;
	
	private String nombre;
	
	@OneToOne
    @JoinColumn(name = "categoria_id")
	private Categoria categoria;
	
	@ManyToMany(mappedBy="grupos", fetch=FetchType.EAGER)
	private List<Usuarios> miembros;
	
	@OneToMany(mappedBy="gastoGrupo", fetch=FetchType.EAGER)
	private List<Gasto> gastoGrupal;
	
	public Grupos () {
		this.miembros = new ArrayList<Usuarios>();
		this.gastoGrupal = new ArrayList<Gasto>();
	}
	
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
	
	public Grupos crearGrupo(String nombre, Categoria categoria) {
		this.nombre = nombre;
		this.categoria = categoria;
		return this;
	}
	
	@Override
	public boolean equals(Object grupo){
		Grupos aux = (Grupos) grupo;
	    if(this.id.equals(aux.getId())){
	        return true;
	    }
	    else{
	        return false;
	    }
	}
	public void editarInformacion(String nombre, Categoria categoria) {
		this.setNombre(nombre);
		this.setCategoria(categoria);
	}
	
	public void invitarAGrupo(String username) {}
	public void agregarAGrupo(String username) {}
}
