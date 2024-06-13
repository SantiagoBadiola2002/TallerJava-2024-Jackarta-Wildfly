package org.tallerjava.moduloMediosDePago.dominio;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Data
@Entity(name = "Tarjeta_MediosPago")
@Table(name = "mediosPago_tarjeta")
public class Tarjeta {
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
    private long id;
	
    private int nro;
    private LocalDateTime fechaVto;
    private String nombreCompletoUsuario;
    
    public Tarjeta() {}
    
	public Tarjeta( int nro, LocalDateTime fechaVto, String nombreCompletoUsuario) {

		this.nro = nro;
		this.fechaVto = fechaVto;
		this.nombreCompletoUsuario = nombreCompletoUsuario;
	}

	public int getNro() {
		return nro;
	}

	public void setNro(int nro) {
		this.nro = nro;
	}

	public LocalDateTime getFechaVto() {
		return fechaVto;
	}

	public void setFechaVto(LocalDateTime fechaVto) {
		this.fechaVto = fechaVto;
	}

	public String getNombreCompletoUsuario() {
		return nombreCompletoUsuario;
	}

	public void setNombreCompletoUsuario(String nombreCompletoUsuario) {
		this.nombreCompletoUsuario = nombreCompletoUsuario;
	}
    
    
    
}
