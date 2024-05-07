package ModuloDeClases;

import java.util.Date;

public class Vinculo {
	
	private Date fechaIni;
	private boolean activo;
	private Vehiculo vehiculo;
	private Usuario usuario;
	
	public Vinculo() {
		
	}

	public Vinculo(Date fechaIni, boolean activo, Vehiculo vehiculo, Usuario usuario) {
		super();
		this.fechaIni = fechaIni;
		this.activo = activo;
		this.vehiculo = vehiculo;
		this.usuario = usuario;
	}

	public Date getFechaIni() {
		return fechaIni;
	}

	public boolean isActivo() {
		return activo;
	}

	public Vehiculo getVehiculo() {
		return vehiculo;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setFechaIni(Date fechaIni) {
		this.fechaIni = fechaIni;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}

	public void setVehiculo(Vehiculo vehiculo) {
		this.vehiculo = vehiculo;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	

}
