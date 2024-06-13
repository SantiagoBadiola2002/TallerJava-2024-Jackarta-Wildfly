package org.tallerjava.moduloGestion.interfase.evento.out;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GestionINFONuevoVehiculo {
    //no uso VehiculoDTO para maneter bajo el acoplamiento
    //de lo contrario el modulo de Peaje que es quien va a escuchar este evento
    //va a tener una dependencia transitiva con el DTO
    private int tag;

	private String matricula;
    private int nacionalidad;
    
    
    public GestionINFONuevoVehiculo(int tag, String matricula, int nacionalidad) {
		this.tag = tag;
		this.matricula = matricula;
		this.nacionalidad = nacionalidad;
	}
    
    public String getMatricula() {
    	return this.matricula;
    }
    
    public int getNacionalidad() {
    	return this.nacionalidad;
    }
    
    public int getTag() {
    	return this.tag;
    }
    
}