package org.tallerjava.moduloMediosDePago.dominio;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class PrePaga extends Cuenta {

	private int saldo;


	public PrePaga(long id, long nroCuenta, LocalDateTime fecha, int i) {
		 super(id, nroCuenta, fecha);
		this.saldo = i;
	}



	public void descontarSaldo(double importe) {
		this.saldo -= importe;
	}
	

	public int getSaldo() {
		return this.saldo;
	}
}
