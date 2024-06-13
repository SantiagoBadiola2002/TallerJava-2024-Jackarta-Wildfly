package org.tallerjava.moduloPeaje.dominio;

import java.time.LocalDateTime;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;

@Entity
@DiscriminatorValue("comun")
public class Comun extends Tarifa{
	
	public Comun() {}
    
    public Comun(LocalDateTime fecha, double monto) {
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
