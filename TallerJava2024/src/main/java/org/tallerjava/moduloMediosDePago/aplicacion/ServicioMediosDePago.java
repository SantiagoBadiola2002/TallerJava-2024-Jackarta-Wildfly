package org.tallerjava.moduloMediosDePago.aplicacion;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.tallerjava.moduloMediosDePago.dominio.*;
import org.tallerjava.moduloMediosDePago.interfase.remota.rest.dto.DTPagos;

public interface ServicioMediosDePago {
	
	public void altaCliente(int idCliente, int nroTarjeta, LocalDateTime fechaVtoTarjeta, String nombreCompletoUsuario); //Da de alta al cliente
	public void altaVehiculo(int idCliente, String matricula, int tag);
	
	
	public String notificarPago(int idCliente, int tag, double importe, int nroTarjeta); //notifica del pago al Sistema externo de Medios De Pago
	
	public List<DTPagos> consultaDePagos(LocalDate fechaInicial, LocalDate fechaFinal); //importe total de pagos realizados agrupados  por día
	public List<DTPagos> consultaDePagos(int idCliente); //devuelve los pagos realizados por un Cliente en particular
	public List<DTPagos> consultaDePagos(int idCliente, int tag); //devuelve los pagos realizados por un vehículo determinado de un Cliente en particular
	
	
	
}
