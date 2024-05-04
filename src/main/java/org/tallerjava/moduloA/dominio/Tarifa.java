package org.tallerjava.moduloA.dominio;

public class Tarifa {
	
    private String monto;

    public Tarifa(){
    	
    }
    
    public Tarifa(String monto) {
        this.monto = monto;
    }

    public String getMonto() {
        return monto;
    }

    public void setMonto(String monto) {
        this.monto = monto;
    }
}

