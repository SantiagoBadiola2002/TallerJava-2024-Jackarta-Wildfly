package org.tallerjava.moduloPeaje.infraestructura.persistencia;

import java.util.ArrayList;
import java.util.List;

import org.tallerjava.moduloPeaje.dominio.*;
import org.tallerjava.moduloPeaje.dominio.repo.PeajeRepositorio;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PeajeRepositorioImpl implements PeajeRepositorio {
	
	
	     List<Vehiculo> vehiculos;
	     Comun tarifaComun;
	     Preferencial tarifaPreferencial;

	    @PostConstruct
	    public void init() {
	        
	        vehiculos.add(new Vehiculo(1, new Identificador(1, "baa 4444", 10001), "Ford", "Fiesta", Nacionalidad.NACIONAL));
	        vehiculos.add(new Vehiculo(2, new Identificador(2, "caa 555", 10000), "Ford", "Fiesta2", Nacionalidad.NACIONAL));
	        
	        
	        tarifaComun = new Comun(150);
	        tarifaPreferencial = new Preferencial(100);
	    }
	    
    @Override
    public Vehiculo findByTag(int tag) {
        for (Vehiculo vehiculo : vehiculos) {
            if (vehiculo.getIdentificador().getTag() == tag) {
                return vehiculo;
            }
        }
        return null;
    }

    @Override
    public Vehiculo findByMatricula(String matricula) {
        for (Vehiculo vehiculo : vehiculos) {
            if (vehiculo.getIdentificador().getMatricula().equals(matricula)) {
                return vehiculo;
            }
        }
        return null; 
    }

    @Override
    public Comun obtenerTarifaComun() {
        return tarifaComun;
    }

    @Override
    public Preferencial obtenerTarifaPreferencial() {
        return tarifaPreferencial;
    }
    
    @Override
    public void actualizarTarifaComun(double valor) {
        tarifaComun.setValor(valor);
        // Implementar la lógica de actualización en la base de datos si es necesario
    }
    
    @Override
    public void actualizarTarifaPreferencial(double valor) {
        tarifaPreferencial.setValor(valor);
        // Implementar la lógica de actualización en la base de datos si es necesario
    }
}
