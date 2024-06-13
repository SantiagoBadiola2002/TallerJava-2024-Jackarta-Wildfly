package org.tallerjava.moduloPeaje.dominio;

import java.time.LocalDateTime;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;

@Entity
@DiscriminatorValue("preferencial") 
public class Preferencial extends Tarifa{
	
	public Preferencial() {}
	
    public Preferencial(LocalDateTime fecha, double monto) {
        this.valor = monto;
        this.fechaAplicacion = fecha;
    }

    public double getValor() {
        return this.valor;
    }
    
    public void setValor(double valor) {
        this.valor = valor;
    }
    
}
