package TestModuloPeaje;

import static org.junit.jupiter.api.Assertions.*;

import org.jboss.weld.junit5.auto.AddPackages;
import org.jboss.weld.junit5.auto.EnableAutoWeld;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.tallerjava.moduloMonitoreo.interfase.evento.escuchar.ObserverPeajeVehiculoNoEncontrado;
import org.tallerjava.moduloPeaje.aplicacion.ServicioPeaje;
import org.tallerjava.moduloPeaje.aplicacion.impl.ServicioPeajeImpl;
import org.tallerjava.moduloPeaje.dominio.repo.PeajeRepositorio;
import org.tallerjava.moduloPeaje.infraestructura.persistencia.PeajeRepositorioImpl;

import jakarta.inject.Inject;

@EnableAutoWeld
//@AddPackages(ServicioPeajeImpl.class)
//@AddPackages(PeajeRepositorioImpl.class)

class TestServicioPeaje {

//	@Inject
//    private ServicioPeaje SPeaje;
//	@Inject
//    private PeajeRepositorio repo;
//	@Inject
//    private ObserverPeajeVehiculoNoEncontrado ObsPeaje;


	
	@Test
	@DisplayName("######Test del Servicio Modulo Peaje######")
	void test() {
	//	boolean habilitado = SPeaje.estaHabilitadoSincronico(1,"ccc4444");
		System.out.println("llegue aca");
	}

}


