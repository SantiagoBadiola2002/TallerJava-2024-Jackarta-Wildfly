package org.tallerjava.moduloA.dominio;

import java.sql.Date;

class Tarjeta {
    private int nroTarjeta;
    private String nombre;
    private Date fechaVencimiento;

    public Tarjeta(int nroTarjeta, String nombre, Date fechaVencimiento) {
        this.nroTarjeta = nroTarjeta;
        this.nombre = nombre;
        this.fechaVencimiento = fechaVencimiento;
    }

    public int getNroTarjeta() {
        return nroTarjeta;
    }

    public void setNroTarjeta(int nroTarjeta) {
        this.nroTarjeta = nroTarjeta;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Date getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(Date fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    @Override
    public String toString() {
        return "Tarjeta{" +
                "nroTarjeta=" + nroTarjeta +
                ", nombre='" + nombre + '\'' +
                ", fechaVencimiento=" + fechaVencimiento +
                '}';
    }
}