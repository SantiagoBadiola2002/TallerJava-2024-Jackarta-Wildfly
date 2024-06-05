package org.tallerjava.moduloGestion.dominio;

import java.util.List;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("Extranjero")
public class Extranjero extends Usuario {
	
	public Extranjero() {
		
	}

	public Extranjero(long id, String nombre, String email, List<Vehiculo> vehiculos,
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
