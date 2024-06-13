package org.tallerjava.moduloMediosDePago.dominio;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "mediosPago_pago")
public class Pago {
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private int id;
	
	private LocalDateTime fecha;
	@ManyToOne
	private Cliente cliente;
	
	@OneToOne
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
