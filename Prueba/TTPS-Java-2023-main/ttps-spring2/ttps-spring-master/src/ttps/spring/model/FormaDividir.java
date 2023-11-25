package ttps.spring.model;

import jakarta.persistence.*;

@Entity
@Table(name="FormasDividir")
public class FormaDividir {
	
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="formaDividir_id")
	private Long id;
	
	private String nombre;
	
	public FormaDividir () {}
	
	public double dividirGasto(Long id) {
		return 0;
	}
}
