package org.tallerjava.moduloGestion.dominio;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@Entity
@Table(name = "gestion_cuentaPrePaga")
public class PrePaga extends Cuenta {

	private int saldo;
	
	public PrePaga(){
		
	}


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
