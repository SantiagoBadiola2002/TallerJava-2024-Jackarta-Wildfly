package org.tallerjava.moduloPeaje.dominio;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;

@Entity
@DiscriminatorValue("comun")
public class Comun extends Tarifa{
    
	public Comun(double valor) {
        this.valor = valor;
    }
	
    public double getValor() {
        return this.valor;
    }
    public void setValor(double valor) {
        this.valor = valor;
    }
}
