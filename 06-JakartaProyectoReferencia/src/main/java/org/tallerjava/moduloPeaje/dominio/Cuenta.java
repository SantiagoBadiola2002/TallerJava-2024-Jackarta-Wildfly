package org.tallerjava.moduloPeaje.dominio;

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
    

}
