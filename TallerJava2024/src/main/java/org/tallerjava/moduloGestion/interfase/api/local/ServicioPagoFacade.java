package org.tallerjava.moduloGestion.interfase.api.local;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.tallerjava.moduloGestion.aplicacion.ServicioPago;
import org.tallerjava.moduloGestion.dominio.*;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Si bien esta fachada es un pasamano, notese que su utilidad esta relacionada
 * a mantener claridad conceptual (un modulo solo se comunica con otro a traves de su
 * capa de interface (o eventos)
 *
 * Si no queremos ser tan puristas, podemos omitir este nivel de abstracción y acceder
 * desde el modulo cliente directamente a la capa de aplicación.
 */
@ApplicationScoped
public class ServicioPagoFacade {
	
    @Inject
    private ServicioPago servicioPago;

    public boolean realizarPrePago(int tag, double importe) {

        return servicioPago.realizarPrePago(tag, importe);
    }

    public boolean realizarPostPago(int tag, double importe) {

        return servicioPago.realizarPostPago(tag, importe);
    }

    public boolean esClienteTelepeaje(int tag) {
        return servicioPago.esClienteTelepeaje(tag);
    }
    
    public void altaClienteTelepeaje(Usuario usr) {
    	servicioPago.altaClienteTelepeaje(usr);
    };
    
    public boolean vincularVehiculo(int idCliente, int tag, String matricula) {
    	return servicioPago.vincularVehiculo(idCliente,tag,matricula);
    };
    
	public boolean desvincularVehiculo(int idCliente, int tag, String matricula) {
		return servicioPago.desvincularVehiculo(idCliente, tag, matricula);
	}
	
    public List<Integer> obtenerCuentasPorTag(int tag){
    	return servicioPago.obtenerCuentasPorTag(tag);
    }

	public void agregarTarjeta(int ci, int nroTarjeta, LocalDateTime fechaVtoTarjeta, String nombreCompletoUsuario) {
		servicioPago.agregarTarjeta(ci, nroTarjeta, fechaVtoTarjeta, nombreCompletoUsuario);
	}

	public List<PasadaPeaje> consultarPasadas(int idCliente, LocalDateTime fechaInicial, LocalDateTime fechaFinal) {
		return servicioPago.consultarPasadas(idCliente, fechaInicial, fechaFinal);
	}

	public List<PasadaPeaje> consultarPasadas(int idCliente, int tag, String matricula, LocalDateTime fechaInicial,
			LocalDateTime fechaFinal) {
		return servicioPago.consultarPasadas(idCliente, tag, matricula, fechaInicial, fechaFinal);
	}

	public double cargarSaldo(int idCliente, double importe) {
		return servicioPago.cargarSaldo(idCliente, importe);	
	}

	public double consultarSaldo(int idCliente) {
		return servicioPago.consultarSaldo(idCliente);
	}
  
}
