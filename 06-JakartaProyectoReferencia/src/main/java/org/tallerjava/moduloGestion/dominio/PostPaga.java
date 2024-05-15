package org.tallerjava.moduloGestion.dominio;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PostPaga extends Cuenta{


	private Tarjeta tarjeta;


	 public PostPaga(long id, long nroCuenta, LocalDateTime fechaApertura, Tarjeta tarjeta) {
	        super(id, nroCuenta, fechaApertura); // Inicializa los atributos heredados
	        this.tarjeta = tarjeta;
	    }


	public Tarjeta getTarjeta() {
		return tarjeta;
	}

	 
	
	 
	
	
	
	
	

}
