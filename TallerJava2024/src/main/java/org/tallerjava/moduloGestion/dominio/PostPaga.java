package org.tallerjava.moduloGestion.dominio;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@Entity
@Table (name = "gestion_cuentaPostPaga")
public class PostPaga extends Cuenta{

	@OneToOne
	@JoinColumn(name = "idTarjeta", referencedColumnName = "id")
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
