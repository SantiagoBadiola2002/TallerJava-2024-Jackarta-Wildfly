package org.tallerjava.moduloGestion.dominio;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@Entity
@Table(name = "gestion_clienteSucive")
public class ClienteSucive {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idClienteSucive;
	
	@OneToOne
    private Nacional usuarioNacional;

    public ClienteSucive(Nacional usrNacional) {
    	this.usuarioNacional = usrNacional;
    	
    }

}


