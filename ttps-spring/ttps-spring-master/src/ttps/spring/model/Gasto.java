package ttps.spring.model;

import java.util.Date;

import jakarta.persistence.*;

@Entity
@Table(name="Gastos")
public class Gasto {
	
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="gasto_id")
	private Long id;
	
	private Long userGasto;
	private Date fecha;
	private double monto;
	
	@OneToOne
    @JoinColumn(name = "categoria_id")
	private Categoria categoria;
	
	private String tipo;
	
	@OneToOne
    @JoinColumn(name = "forma_dividir_id")
	private FormaDividir formaDividir;
	
	private int fmValor;
	
	@ManyToOne
	@JoinColumn(name="gasto_persona_id")
	private Usuarios gastoPersona;
	
	@ManyToOne
	@JoinColumn(name="gasto_grupo_id")
	private Grupos gastoGrupo;
	
	public Gasto () {}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getUserGasto() {
		return userGasto;
	}
	public void setUserGasto(Long userGasto) {
		this.userGasto = userGasto;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public double getMonto() {
		return monto;
	}
	public void setMonto(double monto) {
		this.monto = monto;
	}
	public Categoria getCategoria() {
		return categoria;
	}
	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public FormaDividir getFormaDividir() {
		return formaDividir;
	}
	
	public int getFDValor() {
		return fmValor;
	}
	public void setFDValor(int valor) {
		this.fmValor = valor;
	}
	
	public Gasto crearGasto(Long idUser, 
						    Date fecha, 
						    double monto, 
						    Categoria categoria, 
						    String tipo, 
						    FormaDividir fd, 
						    int fdV, 
						    Usuarios user,
						    Grupos grupo) {
		this.userGasto = idUser;
		this.fecha = fecha;
		this.monto = monto;
		this.categoria = categoria;
		this.tipo = tipo;
		this.formaDividir = fd;
		this.fmValor = fdV;
		this.gastoPersona = user;
		this.gastoGrupo = grupo;
		return this;
	}
}
