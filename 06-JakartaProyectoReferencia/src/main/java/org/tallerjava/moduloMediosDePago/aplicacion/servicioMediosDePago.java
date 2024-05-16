package org.tallerjava.moduloMediosDePago.aplicacion;

import org.tallerjava.moduloMediosDePago.dominio.*;

public interface servicioMediosDePago {
	
	public void altaCliente(ClienteTelepeaje clienteTelepeaje, Tarjeta tarjeta); //Da de alta al cliente
	public void notificarPago(ClienteTelepeaje clienteTelepeaje, Vehiculo vehiculo, double importe, Tarjeta tarjeta); //notifica del pago al Sistema externo de Medios De Pago
	//consultaDePagos(rangoFechas):set(Pagos); //importe total de pagos realizados agrupados  por día
	//consultaDePagos(Cliente):set(Pagos); //devuelve los pagos realizados por un Cliente en particular
	//consultaDePagos(Cliente, Vehículo):set(Pagos); //devuelve los pagos realizados por un vehículo determinado de un Cliente en particular
	



}
