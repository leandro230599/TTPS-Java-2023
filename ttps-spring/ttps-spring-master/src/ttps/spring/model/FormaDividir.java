package ttps.spring.model;

import jakarta.persistence.*;

@Entity
@Table(name="FormasDividir")
public class FormaDividir {
	
	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="formaDividir_id")
	private Long id;
	
	private String nombre;
	
	public FormaDividir () {}

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
	
}
