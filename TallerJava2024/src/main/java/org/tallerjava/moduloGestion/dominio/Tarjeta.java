package org.tallerjava.moduloGestion.dominio;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Data
@Entity
@Table (name = "gestion_tarjeta")
public class Tarjeta {
	
	@Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private long id;
	
    private int nro;
    private LocalDateTime fechaVto;
    private String nombreCompletoUsuario;
    
	public Tarjeta(long id, int nro, LocalDateTime fechaVto, String nombreCompletoUsuario) {
		this.id = id;
		this.nro = nro;
		this.fechaVto = fechaVto;
		this.nombreCompletoUsuario = nombreCompletoUsuario;
	}
	
	public Tarjeta(int nro, LocalDateTime fechaVto, String nombreCompletoUsuario) {
		this.nro = nro;
		this.fechaVto = fechaVto;
		this.nombreCompletoUsuario = nombreCompletoUsuario;
	}
    
    public int getIdTarjeta() {
    	return (int) this.id;
    }
    
    public int getNroTarjeta() {
    	return this.nro;
    }
    
    public LocalDateTime getFechaVto() {
    	return this.fechaVto;
    }

    public String getNombreCompleto() {
    	return this.nombreCompletoUsuario;
    }


}
