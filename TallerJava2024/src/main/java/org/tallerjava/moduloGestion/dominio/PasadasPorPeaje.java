package org.tallerjava.moduloGestion.dominio;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity (name = "gestion_PasadasPorPeaje")
public class PasadasPorPeaje {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int idPasadas;
	
	LocalDateTime fecha;
	double costo;
	DTTipoCobro tipoCobro;
	
	public PasadasPorPeaje(LocalDateTime fecha, double costo, DTTipoCobro tipoCobro) {
		this.fecha = fecha;
		this.costo = costo;
		this.tipoCobro = tipoCobro;
	}

	public LocalDateTime getFecha() {
		return fecha;
	}

	public double getCosto() {
		return costo;
	}

	public DTTipoCobro getTipoCobro() {
		return tipoCobro;
	}

	public void setFecha(LocalDateTime fecha) {
		this.fecha = fecha;
	}

	public void setCosto(double costo) {
		this.costo = costo;
	}

	public void setTipoCobro(DTTipoCobro tipoCobro) {
		this.tipoCobro = tipoCobro;
	}
	
    
}
