package org.tallerjava.moduloGestion.dominio;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PrePaga extends Cuenta {

	private int saldo;

	public PrePaga(int i) {
		this.saldo = i;
	}

	public PrePaga() {

	}

	public void descontarSaldo(double importe) {
		this.saldo -= importe;
	}
	
	
}
