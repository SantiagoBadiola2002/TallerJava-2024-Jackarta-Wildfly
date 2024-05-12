package org.tallerjava.moduloPeaje.infraestructura.persistencia;

import org.tallerjava.moduloPeaje.dominio.*;
import org.tallerjava.moduloPeaje.dominio.repo.PeajeRepositorio;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PeajeRepositorioImpl implements PeajeRepositorio {
	
    @Override
    public Vehiculo findByTag(int tag) {
        Vehiculo vehiculo = new Vehiculo(1,
                new Identificador(1,"baa 4444", 10001),
                 "ford", "fiesta", Nacionalidad.NACIONAL);

        return vehiculo;
    }

    @Override
    public Vehiculo findByMatricula(String matricula) {
    	 Vehiculo vehiculo = new Vehiculo(2,
                 new Identificador(2,"caa 555", 10000),
                  "ford2", "fiesta2", Nacionalidad.NACIONAL);
    	 return vehiculo;
    }

    @Override
    public Preferencial obtenerTarifaPreferencial() {
        return new Preferencial(100);
    }

    @Override
    public Comun obtenerTarifaComun() {
        return new Comun(150);
    }
}
