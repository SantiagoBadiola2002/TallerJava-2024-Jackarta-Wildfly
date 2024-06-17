package org.tallerjava.moduloGestion.dominio;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "gestion_pasadaPeaje")
public class PasadaPeaje {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int idPasada;
	
	LocalDateTime fecha;
	double costo;
	DTTipoCobro tipoCobro;
	@ManyToOne
	Vehiculo vehiculo;
	
	public PasadaPeaje() {}
	
	public PasadaPeaje(LocalDateTime fecha, double costo, DTTipoCobro tipoCobro, Vehiculo vehiculo) {
		this.fecha = fecha;
		this.costo = costo;
		this.tipoCobro = tipoCobro;
		this.vehiculo = vehiculo;
	}

	public int getIdPasada() {
		return idPasada;
	}

	public void setIdPasada(int idPasada) {
		this.idPasada = idPasada;
	}

	public Vehiculo getVehiculo() {
		return vehiculo;
	}

	public void setVehiculo(Vehiculo vehiculo) {
		this.vehiculo = vehiculo;
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
