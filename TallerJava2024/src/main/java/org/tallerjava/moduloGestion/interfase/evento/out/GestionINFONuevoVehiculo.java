package org.tallerjava.moduloGestion.interfase.evento.out;

import lombok.Data;

@Data

public class GestionINFONuevoVehiculo {
    //no uso VehiculoDTO para maneter bajo el acoplamiento
    //de lo contrario el modulo de Peaje que es quien va a escuchar este evento
    //va a tener una dependencia transitiva con el DTO
    private int tag;

	private String matricula;
    private int nacionalidad;
    
    private int idCliente;
    
    
    public GestionINFONuevoVehiculo(int tag, String matricula, int nacionalidad, int idCliente) {
		this.tag = tag;
		this.matricula = matricula;
		this.nacionalidad = nacionalidad;
		this.idCliente = idCliente;
	}
    
    public int getIdCliente() {
		return idCliente;
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