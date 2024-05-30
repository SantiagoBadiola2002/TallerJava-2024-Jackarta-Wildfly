package org.tallerjava.moduloGestion.dominio;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table (name = "gestion_vinculo")
public class Vinculo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int idVinculo;
	
	@ManyToOne
	private Usuario usuario;
	
	private LocalDateTime fechaini;
	private boolean activo;
	
	@OneToOne
	private Vehiculo vehiculo;
	
	public Vinculo(Usuario usuario,LocalDateTime fechaini, boolean activo, Vehiculo vehiculo) {
		this.usuario = usuario;
		this.fechaini = fechaini;
		this.activo = activo;
		this.vehiculo = vehiculo;
	}

	public LocalDateTime getFechaini() {
		return fechaini;
	}

	public boolean isActivo() {
		return activo;
	}

	public Vehiculo getVehiculo() {
		return vehiculo;
	}

	public void setFechaini(LocalDateTime fechaini) {
		this.fechaini = fechaini;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}

	public void setVehiculo(Vehiculo vehiculo) {
		this.vehiculo = vehiculo;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
}
