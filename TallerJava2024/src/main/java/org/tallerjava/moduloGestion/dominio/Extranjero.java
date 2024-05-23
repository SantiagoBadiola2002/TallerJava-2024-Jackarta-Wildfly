package org.tallerjava.moduloGestion.dominio;

public class Extranjero extends Usuario {

	public Extranjero(String nombre, String email) {
		this.nombre = nombre;
		this.email = email;
	}
	
    @Override
    public boolean soyNacional() {
        return false;
    }
}
