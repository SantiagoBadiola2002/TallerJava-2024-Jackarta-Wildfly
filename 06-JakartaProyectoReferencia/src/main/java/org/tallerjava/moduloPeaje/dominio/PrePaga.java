package org.tallerjava.moduloPeaje.dominio;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;


public class PrePaga extends Cuenta {

	private int saldo;


	public PrePaga(long id, long nroCuenta, LocalDateTime fecha, int i) {
		 super(id, nroCuenta, fecha);
		this.saldo = i;
	}



	public int getSaldo() {
		return saldo;
	}



	public void setSaldo(int saldo) {
		this.saldo = saldo;
	}



	public void descontarSaldo(double importe) {
		this.saldo -= importe;
	}
	
	
}