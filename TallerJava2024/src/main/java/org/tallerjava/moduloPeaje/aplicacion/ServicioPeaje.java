package org.tallerjava.moduloPeaje.aplicacion;

import org.tallerjava.moduloPeaje.dominio.Vehiculo;
import org.tallerjava.moduloPeaje.infraestructura.messaging.PagoRealizadoMessage;
import org.tallerjava.moduloPeaje.interfase.remota.rest.dto.DTVehiculo;

public interface ServicioPeaje {
	
    public boolean estaHabilitadoSincronico(int tag, String matricula);
    public void actualizarTarifaComun(double importe);
    public void actualizarTarifaPreferencial(double importe);
    void altaVehiculo(Vehiculo vehiculo);
    public boolean  procesarVehiculoNacional(PagoRealizadoMessage pago);
}
