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
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS) // Puede ser JOINED, SINGLE_TABLE, o 

public abstract class Usuario {
	
	@Id
    @GeneratedValue (strategy = GenerationType.TABLE)

    protected int id; 
	
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

	public String getNombre() {
    	return this.nombre;
    }
	public String getEmail() {
    	return this.email;
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
