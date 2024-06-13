package org.tallerjava.moduloMediosDePago.dominio;

import java.util.List;

import org.tallerjava.moduloMediosDePago.dominio.*;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;


@Data
@Entity
@Table(name = "mediosPago_cliente")
public class Cliente {
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private int id;
	//idCliente es el idCliTelepeaje
	private int idCliente;

	@OneToOne
	private Tarjeta tarjeta;
	
	@OneToMany(mappedBy = "cliente")
	private List<Pago> pagos;
	
	public Cliente() {}
	
	public Cliente( int idCliente,  Tarjeta tarjeta, List<Pago> pagos) {
		this.tarjeta = tarjeta;
		this.idCliente = idCliente;

		this.pagos = pagos;
	}


	public Tarjeta getTarjeta() {
		return tarjeta;
	}

	public int getIdCliente() {
		return idCliente;
	}

	public List<Pago> getPagos() {
		return pagos;
	}

	public void setTarjeta(Tarjeta tarjeta) {
		this.tarjeta = tarjeta;
	}

	public void setIdCliente(int ciCliente) {
		this.idCliente = ciCliente;
	}

	public void setPagos(List<Pago> pagos) {
		this.pagos = pagos;
	}
	
	

}
