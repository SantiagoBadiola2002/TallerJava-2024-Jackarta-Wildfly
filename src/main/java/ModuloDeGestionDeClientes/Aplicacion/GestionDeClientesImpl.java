package ModuloDeGestionDeClientes.Aplicacion;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ModuloDeClases.ClienteTelepeaje;
import ModuloDeClases.Extranjero;
import ModuloDeClases.Matricula;
import ModuloDeClases.Nacional;
import ModuloDeClases.PasadaPorPeaje;
import ModuloDeClases.Tag;
import ModuloDeClases.Tarjeta;
import ModuloDeClases.UsrExtranjero;
import ModuloDeClases.UsrNacional;
import ModuloDeClases.Usuario;
import ModuloDeClases.Vehiculo;
import ModuloDeClases.Vinculo;
import jakarta.annotation.PostConstruct;

public class GestionDeClientesImpl implements GestionDeClientesAplicacion {

	List<UsrNacional> usrNacionales;
	List<UsrExtranjero> usrExtranjeros;
	List<ClienteTelepeaje> clientesTelepeaje;
	List<Vinculo> vinculos;

	@PostConstruct
	private void inicializar() {
		System.out.println("Invocando PostConstruct");
		this.usrNacionales = new ArrayList<UsrNacional>();
		this.usrExtranjeros = new ArrayList<UsrExtranjero>();
		this.vinculos = new ArrayList<Vinculo>();

		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");

		

		Nacional vehiculoNac1 = new Nacional(new Matricula("Mat1"), new Tag("TagNac1"));
		Nacional vehiculoNac2 = new Nacional(new Matricula("Mat2"), new Tag("TagNac2"));
		Nacional vehiculoNac3 = new Nacional(new Matricula("Mat3"), new Tag("TagNac3"));
		Nacional vehiculoNac4 = new Nacional(new Matricula("Mat4"), new Tag("TagNac4"));

		Extranjero vehiculoExtr1 = new Extranjero(new Tag("TagExtr1"));
		Extranjero vehiculoExtr2 = new Extranjero(new Tag("TagExtr2"));
		Extranjero vehiculoExtr3 = new Extranjero(new Tag("TagExtr3"));

		UsrNacional usrNac1 = new UsrNacional("111", "UsuarioNacional1", "nacional1@example.com");
		UsrNacional usrNac2 = new UsrNacional("222", "UsuarioNacional2", "nacional2@example.com");
		UsrNacional usrNac3 = new UsrNacional("333", "UsuarioNacional3", "nacional3@example.com");
		
		UsrExtranjero usrExtr1 = new UsrExtranjero("444", "UsuarioExtranjero1", "extranjero1@example.com");
        UsrExtranjero usrExtr2 = new UsrExtranjero("555", "UsuarioExtranjero2", "extranjero2@example.com");
        UsrExtranjero usrExtr3 = new UsrExtranjero("666", "UsuarioExtranjero3", "extranjero3@example.com");
		

		@SuppressWarnings("deprecation")
		Date fechaManual = new Date(124, 4, 7);
		
		Vinculo vinculo1 = new Vinculo((fechaManual), true, vehiculoNac1, usrNac1);
		Vinculo vinculo2 = new Vinculo((fechaManual), true, vehiculoNac2, usrNac1);
		Vinculo vinculo3 = new Vinculo((fechaManual), true, vehiculoNac2, usrNac2);
		
		Vinculo vinculo4 = new Vinculo((fechaManual), true, vehiculoExtr1, usrExtr1);
		
		
		this.vinculos.add(vinculo1);
		this.vinculos.add(vinculo2);
		usrNac1.setVinculos(vinculos);
		
		this.vinculos.clear();
		this.vinculos.add(vinculo3);
		usrNac2.setVinculos(vinculos);
		
		this.vinculos.clear();
		this.vinculos.add(vinculo4);
		usrExtr1.setVinculos(vinculos);

		usrNacionales.add(usrNac1);
		usrNacionales.add(usrNac2);
		usrNacionales.add(usrNac3);

		usrExtranjeros.add(usrExtr1);
		usrExtranjeros.add(usrExtr2);
		usrExtranjeros.add(usrExtr3);

	}

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
