package ejemplo00.aplicacion.impl;

import java.util.ArrayList;
import java.util.List;

import ejemplo00.aplicacion.ServicioMedioDePago;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;

/**
 * Implementación solo con fines demostrativos.

 */
@ApplicationScoped
public class ServicioMedioDePagoImpl implements ServicioMedioDePago {
	
	@PostConstruct
	private void inicializar() {
		System.out.println("Invocando PostConstruct");
	
	}
	
	
}
