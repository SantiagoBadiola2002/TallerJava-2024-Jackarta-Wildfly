package ModuloDeClases;

import java.util.Date;

import jakarta.inject.Named;

@Named
public class POSTPaga extends Cuenta {
	
	private Tarjeta tarjeta;

    public POSTPaga(int nroCuenta, Date fechaApertura, Tarjeta tarjeta) {
        super(nroCuenta, fechaApertura);
        this.tarjeta = tarjeta;
    }

    public Tarjeta getTarjeta() {
        return tarjeta;
    }

    public void setTarjeta(Tarjeta tarjeta) {
        this.tarjeta = tarjeta;
    }

    @Override
    public String toString() {
        return "POSTPaga{" +
                "nroCuenta=" + getNroCuenta() +
                ", fechaApertura=" + getFechaApertura() +
                ", tarjeta=" + tarjeta.getNroTarjeta() +
                '}';
    }
}
