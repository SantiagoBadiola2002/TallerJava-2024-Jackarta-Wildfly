package org.tallerjava.moduloGestion.dominio;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Random;

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
    //para primera cuenta al crear el Cli Telepeaje
    public Cuenta(LocalDateTime fechaApertura) {
        this.id = id;
        //numero aleatorio para asignar a cuenta
        LocalDateTime now = LocalDateTime.now();
        long semilla = now.getYear() + now.getMonthValue() + now.getDayOfMonth() + now.getHour() + now.getMinute() + now.getSecond();
        Random random = new Random(semilla);
        this.nroCuenta = Math.abs(random.nextLong());
        
        this.fechaApertura = fechaApertura;
    }
    
    //para cuentas pre y post pagas posteriores (asocio mismo nro cuenta)
    public Cuenta(LocalDateTime fechaApertura, long nroCuenta) {
    	this.fechaApertura = fechaApertura;
    	this.nroCuenta = nroCuenta;
    }
    

	public long getIdCuenta() {
    	return id;
    }

    public long getNroCuenta() {
    	return nroCuenta;
    }
}
