package ttps.spring.clasesDTO;

public class GrupoDTO {
	private Long grupo_id;
	private String nombre;
	private Long categoria_id;
	
	public GrupoDTO() {}
	
	public GrupoDTO(Long grupo_id, String nombre, Long categoria_id) {
		this.grupo_id = grupo_id;
		this.nombre = nombre;
		this.categoria_id = categoria_id;
	}

	public Long getGrupo_id() {
		return grupo_id;
	}

	public String getNombre() {
		return nombre;
	}

	public Long getCategoria_id() {
		return categoria_id;
	}
	
	
}


