package clasesModelo;

import javax.persistence.*;

@Entity
@Table(name="Deudas")
public class Deuda {
	
	@Id @GeneratedValue
	@Column(name="deuda_id")
	private Long id;
	
	private String tipo;
	private Long idGrupoPersona;
	private double monto;
	
	@ManyToOne
	@JoinColumn(name="deuda_persona_id")
	private Usuarios deudaPersona;
	
	public Deuda () {}
	
	public double getMonto() {
		return monto;
	}
	public void setMonto(double monto) {
		this.monto = monto;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public Long getIdGrupoPersona() {
		return idGrupoPersona;
	}
	public void setIdGrupoPersona(Long idGrupoPersona) {
		this.idGrupoPersona = idGrupoPersona;
	}
	
	
}
