package org.tallerjava.moduloMediosDePago.dominio;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

public abstract class Cuenta {
    protected long id;
    protected long nroCuenta;
    protected LocalDateTime fechaApertura;
    
    public Cuenta(long id, long nroCuenta, LocalDateTime fechaApertura) {
        this.id = id;
        this.nroCuenta = nroCuenta;
        this.fechaApertura = fechaApertura;
    }

	public Cuenta(LocalDateTime fechaApertura) {
		this.fechaApertura = fechaApertura;
	}
    

}
