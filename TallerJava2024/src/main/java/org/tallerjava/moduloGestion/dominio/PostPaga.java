package org.tallerjava.moduloGestion.dominio;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class PostPaga extends Cuenta{


	private Tarjeta tarjeta;


	 public PostPaga(LocalDateTime fechaApertura, Tarjeta tarjeta) {
	        super(fechaApertura); // Inicializa los atributos heredados
	        this.tarjeta = tarjeta;
	    }


	public Tarjeta getTarjeta() {
		return tarjeta;
	}
	
	public int getIdTarjeta() {
		return this.tarjeta.getIdTarjeta();
		
	}

	 
	
	 
	
	
	
	
	

}
