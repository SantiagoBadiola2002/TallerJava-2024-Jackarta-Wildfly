package org.tallerjava.moduloGestion.interfase.evento.out;

import org.tallerjava.moduloGestion.dominio.Vehiculo;
import org.tallerjava.moduloGestion.dominio.Tarjeta;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Event;
import jakarta.inject.Inject;

@ApplicationScoped
public class PublicadorEvento {
	
	@Inject
    private Event<GestionERRORClienteTelepeajeNoEncontradoPorTag> cliTelepeajeNoEncontradoPorTag;
	
	@Inject
    private Event<GestionERRORVehiculoTagNoEncontradoPorTag> vehiculoTagNoEncontrado;
	
	@Inject
    private Event<GestionINFONuevoVehiculo> nuevoVehiculo;
	
	@Inject
    private Event<GestionINFONuevaTarjeta> nuevaTarjeta;
	
	@Inject
	private Event<GestionOKPagoPrePago> OKPagoPrePago;
	
	@Inject
	private Event<GestionOKPagoPostPago> OKPagoPostPago;
	
	@Inject
	private Event<GestionERRORSaldoInsuficiente> ERRORSaldoInsuficiente;
	
	

    public void publicarCliTelepeajeTagNoEncontrado(String mensaje){
    	cliTelepeajeNoEncontradoPorTag.fire(new GestionERRORClienteTelepeajeNoEncontradoPorTag(mensaje));
    }

    public void publicarVehiculoTagNoEncontrado(String mensaje){
    	vehiculoTagNoEncontrado.fire(new GestionERRORVehiculoTagNoEncontradoPorTag(mensaje));
    }

	
	public void publicarNotificarPrePago(String mensaje) {
		OKPagoPrePago.fire(new GestionOKPagoPrePago(mensaje));
	}
	
	public void publicarNotificarPostPago(String mensaje) {
		OKPagoPostPago.fire(new GestionOKPagoPostPago(mensaje));
	}
	
	public void publicarNotificarSaldoInsuficiente(String mensaje) {
		ERRORSaldoInsuficiente.fire(new GestionERRORSaldoInsuficiente(mensaje));
	}
	
	//va a obs in Modulo Peaje
	public void publicarNuevoVehiculo(Vehiculo vehiculo){
        GestionINFONuevoVehiculo evento = new GestionINFONuevoVehiculo(
                vehiculo.getIdentificador().getTag(),
                vehiculo.getIdentificador().getMatricula(),
                vehiculo.getCliente().getUsuario().getNacionalidad()
        );

        nuevoVehiculo.fire(evento);
    }
	
	public void publicarNuevaTarjeta(int idCliente , Tarjeta tarjeta) {
		GestionINFONuevaTarjeta evento = new GestionINFONuevaTarjeta(
				idCliente,
				tarjeta.getNroTarjeta(),
				tarjeta.getFechaVto(),
				tarjeta.getNombreCompleto()
				);
		
		nuevaTarjeta.fire(evento);
	}
	
	
}



