package ModuloDeClases;

import java.util.Date;

public class PREPaga extends Cuenta {
	
	private double saldo;

	public PREPaga(int nroCuenta, Date fechaApertura, double saldo) {
		super(nroCuenta, fechaApertura);
		this.saldo = saldo;
	}

	public double getSaldo() {
		return saldo;
	}

	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}

	@Override
	public String toString() {
		return "PREPaga{" + "nroCuenta=" + getNroCuenta() + ", fechaApertura=" + getFechaApertura() + ", saldo=" + saldo
				+ '}';
	}
}