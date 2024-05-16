package org.tallerjava.moduloMediosDePago.aplicacion.impl;

import org.tallerjava.moduloMediosDePago.aplicacion.servicioMediosDePago;
import org.tallerjava.moduloMediosDePago.dominio.ClienteTelepeaje;
import org.tallerjava.moduloMediosDePago.dominio.Tarjeta;
import org.tallerjava.moduloMediosDePago.dominio.Vehiculo;

public class servicioMediosDePagoImpl implements servicioMediosDePago{

	@Override
	public void altaCliente(ClienteTelepeaje clienteTelepeaje, Tarjeta tarjeta) {
		System.out.println("Ponele que estas en el sistema pibe");
		
	}

	@Override
	public void notificarPago(ClienteTelepeaje clienteTelepeaje, Vehiculo vehiculo, double importe, Tarjeta tarjeta) {
		System.out.println("Espero que te haya quedado saldo");
		
	}

}
