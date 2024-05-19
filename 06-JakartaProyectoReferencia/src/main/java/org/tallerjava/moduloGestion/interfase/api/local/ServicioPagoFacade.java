package org.tallerjava.moduloGestion.interfase.api.local;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.tallerjava.moduloGestion.aplicacion.ServicioPago;
import org.tallerjava.moduloGestion.dominio.ClienteTelepeaje;
import org.tallerjava.moduloGestion.dominio.Cuenta;
import org.tallerjava.moduloGestion.dominio.PasadasPorPeaje;
import org.tallerjava.moduloGestion.dominio.Tarjeta;
import org.tallerjava.moduloGestion.dominio.Usuario;
import org.tallerjava.moduloGestion.dominio.Vehiculo;

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
public class ServicioPagoFacade implements ServicioPago {
	
    @Inject
    private ServicioPago servicioPago;

    @Override
    public boolean realizarPrePago(int tag, double importe) {

        return servicioPago.realizarPrePago(tag, importe);
    }

    @Override
    public boolean realizarPostPago(int tag, double importe) {

        return servicioPago.realizarPostPago(tag, importe);
    }

    @Override
    public boolean esClienteTelepeaje(int tag) {
        return servicioPago.esClienteTelepeaje(tag);
    }
    
    @Override
    public void altaClienteTelepeaje(Usuario usr) {
    	servicioPago.altaClienteTelepeaje(usr);
    };
    
    @Override
    public boolean vincularVehiculo(long ci, int tag, String matricula) {
    	return servicioPago.vincularVehiculo(ci,tag,matricula);
    };
    
	@Override
	public boolean desvincularVehiculo(long ci, int tag, String matricula) {
		return servicioPago.desvincularVehiculo(ci, tag, matricula);
	}
	
    @Override
    public List<Integer> obtenerCuentasPorTag(int tag){
    	return servicioPago.obtenerCuentasPorTag(tag);
    }

	@Override
	public void agregarTarjeta(long ci, int nroTarjeta, LocalDateTime fechaVtoTarjeta, String nombreCompletoUsuario) {
		servicioPago.agregarTarjeta(ci, nroTarjeta, fechaVtoTarjeta, nombreCompletoUsuario);
	}

	@Override
	public List<PasadasPorPeaje> consultarPasadas(long ci, LocalDateTime fechaInicial, LocalDateTime fechaFinal) {
		return servicioPago.consultarPasadas(ci, fechaInicial, fechaFinal);
	}

	@Override
	public List<PasadasPorPeaje> consultarPasadas(long ci, int tag, String matricula, LocalDateTime fechaInicial,
			LocalDateTime fechaFinal) {
		return servicioPago.consultarPasadas(ci, tag, matricula, fechaInicial, fechaFinal);
	}

	@Override
	public void cargarSaldo(long ci, double importe) {
		servicioPago.cargarSaldo(ci, importe);	
	}

	@Override
	public double consultarSaldo(long ci) {
		return servicioPago.consultarSaldo(ci);
	}
  
}
