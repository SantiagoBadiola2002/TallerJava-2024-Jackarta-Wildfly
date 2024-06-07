package org.tallerjava.moduloGestion.dominio;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Data
@Entity(name = "nacional")
@Table(name = "gestion_nacional")
public class Nacional extends Usuario {

	
	@OneToOne
	private ClienteSucive clienteSucive;
	
	public Nacional() {
		
	}

	public Nacional(int id, String nombre, String email, List<Vehiculo> vehiculos,
			ClienteTelepeaje cliTelepeaje) {
		this.id = id;
		this.nombre = nombre;
		this.email = email;
		this.vehiculos = vehiculos;
		this.clienteTelepeaje = cliTelepeaje;
	}

	public Nacional(String nombre, String email) {
		this.nombre = nombre;
		this.email = email;
	}

	@Override
	public boolean soyNacional() {
		return true;
	}

	public void setClienteSucive(ClienteSucive cliSucive) {
		this.clienteSucive = cliSucive;

	}

	public ClienteSucive getClienteSucive() {
		return clienteSucive;
	}
	
	
}
