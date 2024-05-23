package org.tallerjava.moduloGestion.interfase.remota.rest.dto;

import org.tallerjava.moduloGestion.dominio.Extranjero;
import org.tallerjava.moduloGestion.dominio.Nacional;
import org.tallerjava.moduloGestion.dominio.Usuario;
import org.tallerjava.moduloPeaje.dominio.Nacionalidad;

import lombok.Data;

@Data
public class DTUsuario {
    private String nombre;
    private String email;
    private int nacionalidad;

    //En este caso, no está mal darle al DTO la responsabilidad de construir
    // su correspondiente objeto de negocio, principalmente porque la función del DTO es la de parsear un json
    public Usuario buildUsuario() {
  
        if(nacionalidad == 1) {
        	return new Nacional(nombre, email);
        } else {
        	return new Extranjero(nombre, email);
        }
    	 
    }
}
