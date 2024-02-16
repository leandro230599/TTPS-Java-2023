package ttps.spring.clasesDTO;

public class CategoriasDTO {
	private Long categoria_id;
	private String imagenURL;
	private String nombre;
	private String tipo;
	
	public CategoriasDTO(){}
	
	public CategoriasDTO(Long catID, String url, String nom, String tipo) {
		this.categoria_id = catID;
		this.imagenURL = url;
		this.nombre = nom;
		this.tipo = tipo;
	}

	public Long getCategoria_id() {
		return categoria_id;
	}

	public String getImagenURL() {
		return imagenURL;
	}

	public String getNombre() {
		return nombre;
	}

	public String getTipo() {
		return tipo;
	}
	
	
}


