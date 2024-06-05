package org.tallerjava.moduloGestion.dominio;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;

@Data
@Entity
@Inheritance (strategy = InheritanceType.JOINED) //los campos comunes en una tabla y los campos de cada subclase
// en tablas separadas

//tablas separadas
@Table(name = "gestion_cuenta")
public abstract class Cuenta {
	
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
    protected long id;
    protected long nroCuenta;
    protected LocalDateTime fechaApertura;
    
    public Cuenta() {
    	
    }
    
    public Cuenta(long id, long nroCuenta, LocalDateTime fechaApertura) {
        this.id = id;
        this.nroCuenta = nroCuenta;
        this.fechaApertura = fechaApertura;
    }

	public Cuenta(LocalDateTime fechaApertura) {
		this.fechaApertura = fechaApertura;
	}
    
    
    

}
