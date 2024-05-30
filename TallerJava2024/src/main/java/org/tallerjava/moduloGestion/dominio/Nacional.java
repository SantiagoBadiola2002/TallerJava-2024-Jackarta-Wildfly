package org.tallerjava.moduloGestion.dominio;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Data
@Entity
@Table(name = "gestion_nacional")
@DiscriminatorValue("Nacional")
public class Nacional extends Usuario {

	
	@OneToOne
	private ClienteSucive clienteSucive;

	public Nacional(long id, String nombre, String email, List<Vinculo> vehiculosVinculados,
			ClienteTelepeaje cliTelepeaje) {
		this.id = id;
		this.nombre = nombre;
		this.email = email;
		this.vehiculosVinculados = vehiculosVinculados;
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
