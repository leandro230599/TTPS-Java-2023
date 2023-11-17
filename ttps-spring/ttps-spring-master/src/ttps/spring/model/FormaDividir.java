package ttps.spring.model;

import javax.persistence.*;

@Entity
@Table(name="FormasDividir")
public class FormaDividir {
	
	@Id @GeneratedValue
	@Column(name="formaDividir_id")
	private Long id;
	
	private String nombre;
	
	public FormaDividir () {}
	
	public double dividirGasto(Long id) {
		return 0;
	}
}
