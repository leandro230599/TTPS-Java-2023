package ttps.spring.model;

import jakarta.persistence.*;

@Entity
@Table(name="Categorias")
public class Categoria {
	
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="categoria_id")
	private Long id;
	
	private String nombre;
	private String imagenURL;
	private String tipo;
	
	public Categoria () {}
	
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
	public String getImagenURL() {
		return imagenURL;
	}
	public void setImagenURL(String imagenURL) {
		this.imagenURL = imagenURL;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	
}
