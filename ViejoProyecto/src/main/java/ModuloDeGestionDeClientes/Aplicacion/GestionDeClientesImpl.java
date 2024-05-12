
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ModuloDeClases.ClienteTelepeaje;
import ModuloDeClases.Extranjero;
import ModuloDeClases.Matricula;
import ModuloDeClases.Nacional;
import ModuloDeClases.POSTPaga;
import ModuloDeClases.PREPaga;
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

//	@PostConstruct
//	private void inicializar() {
//		System.out.println("Invocando PostConstruct");
//		this.usrNacionales = new ArrayList<UsrNacional>();
//		this.usrExtranjeros = new ArrayList<UsrExtranjero>();
//		this.vinculos = new ArrayList<Vinculo>();
//
//		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
//
//		
//
//		Nacional vehiculoNac1 = new Nacional(new Matricula("Mat1"), new Tag("TagNac1"));
//		Nacional vehiculoNac2 = new Nacional(new Matricula("Mat2"), new Tag("TagNac2"));
//		Nacional vehiculoNac3 = new Nacional(new Matricula("Mat3"), new Tag("TagNac3"));
//		Nacional vehiculoNac4 = new Nacional(new Matricula("Mat4"), new Tag("TagNac4"));
//
//		Extranjero vehiculoExtr1 = new Extranjero(new Tag("TagExtr1"));
//		Extranjero vehiculoExtr2 = new Extranjero(new Tag("TagExtr2"));
//		Extranjero vehiculoExtr3 = new Extranjero(new Tag("TagExtr3"));
//
//		UsrNacional usrNac1 = new UsrNacional("111", "UsuarioNacional1", "nacional1@example.com");
//		UsrNacional usrNac2 = new UsrNacional("222", "UsuarioNacional2", "nacional2@example.com");
//		UsrNacional usrNac3 = new UsrNacional("333", "UsuarioNacional3", "nacional3@example.com");
//		
//		UsrExtranjero usrExtr1 = new UsrExtranjero("444", "UsuarioExtranjero1", "extranjero1@example.com");
//        UsrExtranjero usrExtr2 = new UsrExtranjero("555", "UsuarioExtranjero2", "extranjero2@example.com");
//        UsrExtranjero usrExtr3 = new UsrExtranjero("666", "UsuarioExtranjero3", "extranjero3@example.com");
//		
//
//		@SuppressWarnings("deprecation")
//		Date fechaManual = new Date(124, 4, 7);
//		
//		Vinculo vinculo1 = new Vinculo((fechaManual), true, vehiculoNac1, usrNac1);
//		Vinculo vinculo2 = new Vinculo((fechaManual), true, vehiculoNac2, usrNac1);
//		Vinculo vinculo3 = new Vinculo((fechaManual), true, vehiculoNac2, usrNac2);
//		
//		Vinculo vinculo4 = new Vinculo((fechaManual), true, vehiculoExtr1, usrExtr1);
//		
//		
//		this.vinculos.add(vinculo1);
//		this.vinculos.add(vinculo2);
//		usrNac1.setVinculos(vinculos);
//		
//		this.vinculos.clear();
//		this.vinculos.add(vinculo3);
//		usrNac2.setVinculos(vinculos);
//		
//		this.vinculos.clear();
//		this.vinculos.add(vinculo4);
//		usrExtr1.setVinculos(vinculos);
//
//		usrNacionales.add(usrNac1);
//		usrNacionales.add(usrNac2);
//		usrNacionales.add(usrNac3);
//
//		usrExtranjeros.add(usrExtr1);
//		usrExtranjeros.add(usrExtr2);
//		usrExtranjeros.add(usrExtr3);
//
//	}

	@Override
	public void altaClienteTelepeaje(Usuario usuario) {
		ClienteTelepeaje clienteTelepeaje = new ClienteTelepeaje(usuario);
		clientesTelepeaje.add(clienteTelepeaje);
		System.out.print("El usuario " + usuario.getCi() + " ahora es un cliente telepeaje");
	}
	
	@Override
	public void vincularVehiculo(Vehiculo vehiculo, Usuario usuario) {
		Vinculo vinculo = new Vinculo(new Date(), true, vehiculo, usuario);
		vinculos.add(vinculo);
		System.out.print("El usuario " + usuario.getCi() + 
				" ahora esta vinculado con el vehiculo Tag: " + vehiculo.getTag());	
	}
	
	@Override
	public void desvincularVehiculo(Vehiculo vehiculo, Usuario usuario) {
	    for (Vinculo vinculo : vinculos) {
	        if (vinculo.getVehiculo().equals(vehiculo) && vinculo.getUsuario().equals(usuario)) {
	            vinculo.setActivo(false);
	            System.out.println("El usuario " + usuario.getCi()+ " ha sido desvinculado del vehículo Tag: " + vehiculo.getTag());
	            return; 
	        }
	    }
	    System.out.println("No se encontró ningún vínculo activo entre el usuario " + usuario.getCi() + " y el vehículo Tag: " + vehiculo.getTag());
	}
	
	@Override
	public List<Vehiculo> mostrarVehiculosVinculados(Usuario usuario) {
		List<Vehiculo> vehiculosVinculados = new ArrayList<>();
		for (Vinculo vinculo : vinculos) {
			if((vinculo.getUsuario().getCi()).equals(usuario.getCi())) {
				vehiculosVinculados.add(vinculo.getVehiculo());
			}
		}
		return vehiculosVinculados;
	}

	@Override
	public void cargarSaldo(ClienteTelepeaje usuario, Double importe) { //cargaSaldo a la cuenta PRE paga del cliente
		for(ClienteTelepeaje cliente : clientesTelepeaje) {
			if((cliente.getUsuario().getCi()).equals(usuario.getUsuario().getCi())) {
				PREPaga tarjetaPrepaga = cliente.getTarjetaPrePaga();
				double nuevoSaldo = tarjetaPrepaga.getSaldo() + importe;
	            tarjetaPrepaga.setSaldo(nuevoSaldo);
	            System.out.println("Saldo cargado exitosamente. Nuevo saldo: " + nuevoSaldo);
			}
		}

	}

	@Override
	public double consultaSaldo(ClienteTelepeaje usuario) {
		for(ClienteTelepeaje cliente : clientesTelepeaje) {
			if((cliente.getUsuario().getCi()).equals(usuario.getUsuario().getCi())) {
				PREPaga tarjetaPrepaga = cliente.getTarjetaPrePaga();
				return tarjetaPrepaga.getSaldo();
			}
		}
		return 0;
	}


	@Override
	public void asociarTarjeta(ClienteTelepeaje clienteTelepeaje, Tarjeta tarjeta) { //asocia tarjeta de crédito a la cuenta POST paga del cliente
		POSTPaga postPaga = clienteTelepeaje.getTarjetaPostPaga();
		postPaga.setTarjeta(tarjeta);
		System.out.println("La tarjeta " + tarjeta.getNroTarjeta() + " ha sido asociada a la cuenta POSTPaga del usuario "
				+ clienteTelepeaje.getUsuario().getCi());
	}

	@Override
	public List<PasadaPorPeaje> consultarPasadas(ClienteTelepeaje clienteTelepeaje, Date fechaInicio, Date fechaFin) {
	    
		List<PasadaPorPeaje> pasadas = new ArrayList<>(); 
	    List<Vinculo> vinculos = clienteTelepeaje.getUsuario().getVinculos();
	    
	    for (Vinculo vinculo : vinculos) {
	        Vehiculo vehiculo = vinculo.getVehiculo();
	        List<PasadaPorPeaje> pasadasVehiculo = vehiculo.getPasadasPeaje();
	        for (PasadaPorPeaje pasada : pasadasVehiculo) {
	            Date fechaPasada = pasada.getFecha();  
	            if (fechaPasada.after(fechaInicio) && fechaPasada.before(fechaFin)) {
	                pasadas.add(pasada); 
	            }
	        }
	    }
	    
	    return pasadas;
	}


	@Override
	public List<PasadaPorPeaje> consultarPasadas(ClienteTelepeaje usuario, Vehiculo vehiculo, Date fechaInicio, Date fechaFin) {
	    List<PasadaPorPeaje> pasadas = new ArrayList<>();
	    
	    List<Vinculo> vinculos = usuario.getUsuario().getVinculos();
	    
	    for (Vinculo vinculo : vinculos) {
	        Vehiculo vehiculoVinculado = vinculo.getVehiculo();
	        
	        if (vehiculoVinculado.equals(vehiculo)) {
	            List<PasadaPorPeaje> pasadasVehiculo = vehiculoVinculado.getPasadasPeaje();
	            
	            for (PasadaPorPeaje pasada : pasadasVehiculo) {
	                Date fechaPasada = pasada.getFecha();
	                
	                if (fechaPasada.after(fechaInicio) && fechaPasada.before(fechaFin)) {
	                    pasadas.add(pasada); 
	                }
	            }
	            
	            break;
	        }
	    }
	    
	    return pasadas;
	}


	@Override
	public void realizarPrePago(double importe) { // descuenta el importe del pago al saldo del cliente. **Utilizado por el módulo de Peaje.**
      

		

	}

	@Override
	public void realizarPostPago(double importe) { //realiza el pago utilizando tarjeta de crédito
		

	}

	// public Cuenta obtenerCuentasPorTag(Tag tag);//devuelve los tipos de cuentas asociadas al cliente TelepeajeSi la cuenta es de PrePago, devuelve el saldo actual
	//**Utilizado por el módulo de Peaje.**



}
