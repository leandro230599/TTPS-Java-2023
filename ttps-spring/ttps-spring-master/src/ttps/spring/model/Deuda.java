package ttps.spring.model;

import java.util.Date;

import jakarta.persistence.*;

@Entity
@Table(name="Deudas")
public class Deuda {
	
	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="deuda_id")
	private Long id;
	
	private String tipo;
	private Long idGrupoPersona;
	private double monto;
	
	@OneToOne
    @JoinColumn(name = "gasto_id")
	private Gasto gasto;
	
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
	
	public Deuda crearDeuda(Long idGrupoPersona, double monto, String tipo, Usuarios user, Gasto gasto) {
		this.idGrupoPersona = idGrupoPersona;
		this.monto = monto;
		this.tipo = tipo;
		this.deudaPersona = user;
		this.gasto = gasto;
		return this;
	}
	
	public void actualizarDeuda(double monto) {
		this.monto = monto;
	}
	
	
}
