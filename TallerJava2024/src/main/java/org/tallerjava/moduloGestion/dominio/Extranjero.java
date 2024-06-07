package org.tallerjava.moduloGestion.dominio;

import java.util.List;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "gestion_extranjero")
public class Extranjero extends Usuario {
	
	public Extranjero() {
		
	}

	public Extranjero(int id, String nombre, String email, List<Vehiculo> vehiculos,
			ClienteTelepeaje cliTelepeaje) {
		this.id = id;
		this.nombre = nombre;
		this.email = email;
		this.vehiculos = vehiculos;
		this.clienteTelepeaje = cliTelepeaje;
	}
	
	public Extranjero(String nombre, String email) {
		this.nombre = nombre;
		this.email = email;
	}
	
    @Override
    public boolean soyNacional() {
        return false;
    }
}
