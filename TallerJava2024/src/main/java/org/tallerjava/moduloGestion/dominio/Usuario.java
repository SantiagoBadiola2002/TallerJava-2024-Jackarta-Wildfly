package org.tallerjava.moduloGestion.dominio;

import lombok.Getter;

import java.util.List;

import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.DiscriminatorType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table (name = "gestion_usuario")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE) // Puede ser JOINED, SINGLE_TABLE, o TABLE_PER_CLASS
@DiscriminatorColumn(name = "tipo_usuario", discriminatorType = DiscriminatorType.STRING)
public abstract class Usuario {
	
	@Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    protected long id; //CI para nacional y Pasaporte para extranjero
	
    protected String nombre;
    protected String email;
    
    @OneToMany
    protected List<Vehiculo> vehiculos;
    
    @OneToOne
    protected ClienteTelepeaje clienteTelepeaje;

    public abstract boolean soyNacional();
    
	public long getId() {
    	return this.id;
    }

	public ClienteTelepeaje getClienteTelepeaje() {
		return this.clienteTelepeaje;
	}

	public void setClienteTelepeaje(ClienteTelepeaje cliTelepeaje) {
		this.clienteTelepeaje = cliTelepeaje;
		
	}

	public List<Vehiculo> getVehiculos() {
		return vehiculos;
	}

	public void setVehiculos(List<Vehiculo> vehiculos) {
		this.vehiculos = vehiculos;
	}
	
	
}
