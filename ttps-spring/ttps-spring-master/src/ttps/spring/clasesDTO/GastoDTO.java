package ttps.spring.clasesDTO;

import java.sql.Date;

import ttps.spring.model.*;

public class GastoDTO {
		private Long id;
		private Usuarios userGasto;
		private Date fecha;
		private double monto;
		private Categoria categoria;
		private String tipo;	
		private FormaDividir formaDividir;
		private int fmValor;
		
		public GastoDTO (Long id,
						Usuarios userGasto, 
					    Date fecha, 
					    double monto, 
					    Categoria categoria, 
					    String tipo, 
					    FormaDividir fd, 
					    int fdV) {
			this.id = id;
			this.userGasto = userGasto;
			this.fecha = fecha;
			this.monto = monto;
			this.categoria = categoria;
			this.tipo = tipo;
			this.formaDividir = fd;
			this.fmValor = fdV;
		}
		
		public Long getId() {
			return id;
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
	
}


