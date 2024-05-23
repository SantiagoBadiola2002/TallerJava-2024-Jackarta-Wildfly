package org.tallerjava.moduloMediosDePago.dominio;

import java.util.List;

import org.tallerjava.moduloMediosDePago.dominio.*;

public class Cliente {
	
	private String idCliente;
	private Tarjeta tarjeta;
	private long ciCliente;
	private List<Pago> pagos;
	
	public Cliente(Tarjeta tarjeta, long ciCliente, List<Pago> pagos) {
		this.tarjeta = tarjeta;
		this.ciCliente = ciCliente;
		this.pagos = pagos;
	}

	public Tarjeta getTarjeta() {
		return tarjeta;
	}

	public long getCiCliente() {
		return ciCliente;
	}

	public List<Pago> getPagos() {
		return pagos;
	}

	public void setTarjeta(Tarjeta tarjeta) {
		this.tarjeta = tarjeta;
	}

	public void setCiCliente(long ciCliente) {
		this.ciCliente = ciCliente;
	}

	public void setPagos(List<Pago> pagos) {
		this.pagos = pagos;
	}

	public String getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(String idCliente) {
		this.idCliente = idCliente;
	}
	
	

}
