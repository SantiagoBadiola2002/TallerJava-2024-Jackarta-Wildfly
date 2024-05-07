package ModuloDeClases;

public class ClienteTelepeaje {
	
	PREPaga tarjetaPrePaga;
	POSTPaga tarjetaPostPaga;
	
	public ClienteTelepeaje() {
		
	}
	
	public ClienteTelepeaje(PREPaga tarjetaPrePaga) {
		this.tarjetaPrePaga = tarjetaPrePaga;
	}
	
	public ClienteTelepeaje(POSTPaga tarjetaPostPaga) {
		this.tarjetaPostPaga = tarjetaPostPaga;
	}

	public ClienteTelepeaje(PREPaga tarjetaPrePaga, POSTPaga tarjetaPostPaga) {
		super();
		this.tarjetaPrePaga = tarjetaPrePaga;
		this.tarjetaPostPaga = tarjetaPostPaga;
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
	
	
	

}


