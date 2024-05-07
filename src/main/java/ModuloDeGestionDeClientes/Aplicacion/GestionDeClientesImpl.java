package ModuloDeGestionDeClientes.Aplicacion;

import java.util.Date;
import java.util.List;

import ModuloDeClases.ClienteTelepeaje;
import ModuloDeClases.PasadaPorPeaje;
import ModuloDeClases.Tarjeta;
import ModuloDeClases.Usuario;
import ModuloDeClases.Vehiculo;

public class GestionDeClientesImpl implements GestionDeClientesAplicacion{

	@Override
	public void altaClienteTelepeaje(ClienteTelepeaje usuario) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void cargarSaldo(ClienteTelepeaje usuario, Double importe) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public double consultaSaldo(ClienteTelepeaje usuario) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void vincularVehiculo(Vehiculo vehiculo, ClienteTelepeaje usuario) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void desvincularVehiculo(Vehiculo vehiculo, ClienteTelepeaje usuario) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Vehiculo> mostrarVehiculosVinculados(ClienteTelepeaje usuario) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public double asociarTarjeta(Usuario usuario, Tarjeta tarjeta) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<PasadaPorPeaje> consultarPasadas(ClienteTelepeaje usuario, Date fechaInicio, Date fechaFin) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PasadaPorPeaje> consultarPasadas(ClienteTelepeaje usuario, Vehiculo vehiculo, Date fechaInicio,
			Date fechaFin) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void realizarPrePago(double importe) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void realizarPostPago(double importe) {
		// TODO Auto-generated method stub
		
	}
	
	// public Cuenta obtenerCuentasPorTag(Tag tag);

}
