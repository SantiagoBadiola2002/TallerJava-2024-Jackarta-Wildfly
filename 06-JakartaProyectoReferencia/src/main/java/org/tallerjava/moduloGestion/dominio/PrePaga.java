package org.tallerjava.moduloGestion.dominio;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PrePaga extends Cuenta {

	private int saldo;


	public PrePaga(LocalDateTime fecha, int i) {
		 super(fecha);
		this.saldo = i;
	}



	public void descontarSaldo(double importe) {
		this.saldo -= importe;
	}
	
	public void incrementarSaldo(double importe) {
		this.saldo += importe;
	}
	

	public int getSaldo() {
		return this.saldo;
	}
	
	
}
