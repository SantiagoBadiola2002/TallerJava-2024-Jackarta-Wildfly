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

	public Nacional(int id, int nacionalidad, String nombre, String email, ClienteTelepeaje cliTelepeaje) {
		this.id = id;
		this.nacionalidad =0; //0 nacional 1 extranjero
		this.nombre = nombre;
		this.email = email;

		this.clienteTelepeaje = cliTelepeaje;
	}

	public Nacional(String nombre, String email, int nacionalidad) {
		this.nombre = nombre;
		this.email = email;
		this.nacionalidad = nacionalidad;
	}


	public void setClienteSucive(ClienteSucive cliSucive) {
		this.clienteSucive = cliSucive;

	}

	public ClienteSucive getClienteSucive() {
		return clienteSucive;
	}
	
	
}
