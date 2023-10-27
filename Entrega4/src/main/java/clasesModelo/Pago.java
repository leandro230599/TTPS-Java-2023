package clasesModelo;

import javax.persistence.*;

@Entity
@Table(name="Pagos")
public class Pago {
	
	@Id @GeneratedValue
	@Column(name="pago_id")
	private Long id;
	
	private String userPago;
	private double monto;
	private String tipo;
	
	@ManyToOne
	@JoinColumn(name="pago_persona_id")
	private Usuarios pagoPersona;
	
	public Pago () {}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUserPago() {
		return userPago;
	}
	public void setUserPago(String userPago) {
		this.userPago = userPago;
	}
	public double getMonto() {
		return monto;
	}
	public void setMonto(double monto) {
		this.monto = monto;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
}
