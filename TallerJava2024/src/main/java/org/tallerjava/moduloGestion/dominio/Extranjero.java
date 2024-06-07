package org.tallerjava.moduloGestion.dominio;

import java.util.List;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity(name = "extranjero")
@Table(name = "gestion_extranjero")
public class Extranjero extends Usuario {
	
	public Extranjero() {
		
	}

	public Extranjero(int id, int nacionalidad, String nombre, String email, List<Vehiculo> vehiculos,
			ClienteTelepeaje cliTelepeaje) {
		this.id = id;
		this.nacionalidad = 1; //0 nacional 1 extranjero
		this.nombre = nombre;
		this.email = email;
		this.vehiculos = vehiculos;
		this.clienteTelepeaje = cliTelepeaje;
	}
	
	public Extranjero(String nombre, String email, int nacionalidad) {
		this.nombre = nombre;
		this.email = email;
		this.nacionalidad = nacionalidad;
	}
	

}
