package org.tallerjava.moduloGestion.dominio;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PrePaga extends Cuenta {

	private int saldo;


	public PrePaga(long id, long nroCuenta, LocalDateTime fecha, int i) {
		 super(id, nroCuenta, fecha);
		this.saldo = i;
	}



	public void descontarSaldo(double importe) {
		this.saldo -= importe;
	}
	
	
}
