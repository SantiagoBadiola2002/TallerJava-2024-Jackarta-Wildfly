package org.tallerjava.moduloMediosDePago.dominio;

import java.time.LocalDateTime;

public class Pago {
	
	private LocalDateTime fecha;
	private Cliente cliente;
    private Tarjeta tarjeta;
    private String tag;
    private double importe;

	public Pago(LocalDateTime fecha, Cliente cliente, Tarjeta tarjeta, String tag, double importe) {
		this.fecha = fecha;
		this.cliente = cliente;
		this.tarjeta = tarjeta;
		this.tag = tag;
		this.importe = importe;
	}

	public LocalDateTime getFecha() {
		return fecha;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public Tarjeta getTarjeta() {
		return tarjeta;
	}

	public String getTag() {
		return tag;
	}

	public void setFecha(LocalDateTime fecha) {
		this.fecha = fecha;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public void setTarjeta(Tarjeta tarjeta) {
		this.tarjeta = tarjeta;
	}

	public void setTag(String vehiculo) {
		this.tag = vehiculo;
	}

	public double getImporte() {
		return importe;
	}

	public void setImporte(double importe) {
		this.importe = importe;
	}
    
	
    
}
