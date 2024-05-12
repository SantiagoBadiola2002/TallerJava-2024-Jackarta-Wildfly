package ModuloDeClases;

public class ClienteTelepeaje {
	
	Usuario usuario;
	PREPaga tarjetaPrePaga;
	POSTPaga tarjetaPostPaga;
	
	public ClienteTelepeaje() {
		
	}
	
	public ClienteTelepeaje(Usuario usuario) {
		this.usuario = usuario;
	}
	
	public ClienteTelepeaje(PREPaga tarjetaPrePaga) {
		this.tarjetaPrePaga = tarjetaPrePaga;
	}
	
	public ClienteTelepeaje(POSTPaga tarjetaPostPaga) {
		this.tarjetaPostPaga = tarjetaPostPaga;
	}

	public ClienteTelepeaje(PREPaga tarjetaPrePaga, POSTPaga tarjetaPostPaga, Usuario usuario) {
		super();
		this.tarjetaPrePaga = tarjetaPrePaga;
		this.tarjetaPostPaga = tarjetaPostPaga;
		this.usuario = usuario;
	}

	public PREPaga getTarjetaPrePaga() {
		return tarjetaPrePaga;
	}

	public POSTPaga getTarjetaPostPaga() {
		return tarjetaPostPaga;
	}

	public void setTarjetaPrePaga(PREPaga tarjetaPrePaga) {
		this.tarjetaPrePaga = tarjetaPrePaga;
	}

	public void setTarjetaPostPaga(POSTPaga tarjetaPostPaga) {
		this.tarjetaPostPaga = tarjetaPostPaga;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	
	

}


