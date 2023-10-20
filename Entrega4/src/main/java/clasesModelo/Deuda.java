package clasesModelo;

public class Deuda {
	private Long id;
	private String tipo;
	private Long idGrupoPersona;
	private double monto;
	
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
