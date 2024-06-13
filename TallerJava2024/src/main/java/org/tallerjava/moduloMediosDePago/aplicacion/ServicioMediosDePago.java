package org.tallerjava.moduloMediosDePago.aplicacion;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import org.tallerjava.moduloMediosDePago.dominio.*;

public interface ServicioMediosDePago {
	
	public void altaCliente(int idCliente, int nroTarjeta, LocalDateTime fechaVtoTarjeta, String nombreCompletoUsuario); //Da de alta al cliente
	public int notificarPago(int idCliente, Vehiculo vehiculo, double importe, Tarjeta tarjeta); //notifica del pago al Sistema externo de Medios De Pago
	public List<Pago> consultaDePagos(Date fechaInicial, Date fechaFinal); //importe total de pagos realizados agrupados  por día
	public List<Pago> consultaDePagos(Cliente cliente); //devuelve los pagos realizados por un Cliente en particular
	public List<Pago> consultaDePagos(Cliente cliente, Vehiculo vehiculo); //devuelve los pagos realizados por un vehículo determinado de un Cliente en particular
	
}
