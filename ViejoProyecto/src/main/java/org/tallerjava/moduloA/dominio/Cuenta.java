package org.tallerjava.moduloA.dominio;

import java.util.Date;

public class Cuenta {
    private int nroCuenta;
    private Date fechaApertura;

    public Cuenta(int nroCuenta, Date fechaApertura) {
        this.nroCuenta = nroCuenta;
        this.fechaApertura = fechaApertura;
    }

    public int getNroCuenta() {
        return nroCuenta;
    }

    public void setNroCuenta(int nroCuenta) {
        this.nroCuenta = nroCuenta;
    }

    public Date getFechaApertura() {
        return fechaApertura;
    }

    public void setFechaApertura(Date fechaApertura) {
        this.fechaApertura = fechaApertura;
    }

    @Override
    public String toString() {
        return "Cuenta{" +
                "nroCuenta=" + nroCuenta +
                ", fechaApertura=" + fechaApertura +
                '}';
    }
}
