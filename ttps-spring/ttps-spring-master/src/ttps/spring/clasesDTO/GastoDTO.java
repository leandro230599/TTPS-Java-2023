package ttps.spring.clasesDTO;

import java.sql.Date;

import ttps.spring.model.*;

public class GastoDTO {
		private Usuarios userGasto;
		private Date fecha;
		private double monto;
		private Categoria categoria;
		private String tipo;	
		private FormaDividir formaDividir;
		private int fmValor;
		private Long gastoPersona;
		private Long gastoGrupo;
		
		public GastoDTO (Usuarios userGasto, 
					    Date fecha, 
					    double monto, 
					    Categoria categoria, 
					    String tipo, 
					    FormaDividir fd, 
					    int fdV, 
					    Usuarios user,
					    Grupos grupo) {
			this.userGasto = userGasto;
			this.fecha = fecha;
			this.monto = monto;
			this.categoria = categoria;
			this.tipo = tipo;
			this.formaDividir = fd;
			this.fmValor = fdV;
			this.gastoPersona = user.getId();
			this.gastoGrupo = grupo.getId();
		}

		public Usuarios getUserGasto() {
			return userGasto;
		}
		public Date getFecha() {
			return fecha;
		}
		public double getMonto() {
			return monto;
		}
		public Categoria getCategoria() {
			return categoria;
		}
		public String getTipo() {
			return tipo;
		}
		public FormaDividir getFormaDividir() {
			return formaDividir;
		}
		public int getFDValor() {
			return fmValor;
		}

		public Long getGastoPersona() {
			return gastoPersona;
		}

		public Long getGastoGrupo() {
			return gastoGrupo;
		}
	
}


