package org.tallerjava.moduloGestion.infraestructura.persistencia;

import org.tallerjava.moduloGestion.dominio.*;
import org.tallerjava.moduloGestion.dominio.repo.UsuarioRepositorio;

import jakarta.annotation.PostConstruct;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class UsuarioRepositorioImpl implements UsuarioRepositorio {
	
	List<Usuario> usuarios;
	//List<Vehiculo> vehiculos;
	
	
	@PostConstruct
	private void inicializar() {
		System.out.println("Invocando PostConstruct");
		
		LocalDateTime date = LocalDateTime.parse("2024-05-14T10:30:00");
		Tarjeta tarjeta = new Tarjeta(2, 213, date, "JuanchoSuarez");
        PrePaga prePaga = new PrePaga(8676, 9678, date,1000);
        PostPaga postPago = new PostPaga(33, 3423, date, tarjeta);
        ClienteTelepeaje cliTelepeaje = new ClienteTelepeaje(null, prePaga, null);
        List<Vinculo> listVinculos= new ArrayList<>();
        Identificador identificador = new Identificador(1, "BAA 2222", 2001);
        
        LocalDateTime ahora = LocalDateTime.now();
        
        listVinculos.add(new Vinculo(ahora, true, new Vehiculo(1, identificador, cliTelepeaje)));
        
        Usuario usuario = new
                Nacional(1, "pepe","pepe@gmail.com",listVinculos, cliTelepeaje);
		
		usuarios.add(usuario);
		
	}
	
	@Override
	public Usuario findByTag(int tag) {
	    for (Usuario usu : usuarios) {
	        for (Vinculo v : usu.getVehiculosVinculados()) {
	            if (v.getVehiculo().getIdentificador().getTag() == tag) {
	                return usu;
	            }
	        }
	    }
	    return null;
	}


    @Override
    public List<Cuenta> findCuentasByTag(int tag) {
    	Usuario usu = findByTag(tag);
    	List<Cuenta> cuentas = new ArrayList<>();
    	Cuenta cuenta = usu.getClienteTelepeaje().getCtaPostpaga();
    	Cuenta cuenta2 = usu.getClienteTelepeaje().getCtaPrepaga();
    	if(cuenta != null) {
    		cuentas.add(cuenta);
    	}
    	if(cuenta2 != null) {
    		cuentas.add(cuenta2);
    	}
    	return cuentas;
    }
    
    
    @Override
    public ClienteTelepeaje crearClienteTelepeaje(Usuario usr) {
    	//incio saldo prePaga en 0 sin tarjeta
    	LocalDateTime ahora = LocalDateTime.now();

    	if (usr.soyNacional()) {
    		PrePaga prePaga = new PrePaga(1, 0, ahora, 0);
    		ClienteTelepeaje cliTelepeaje = new ClienteTelepeaje(null, prePaga, null);
    		crearClienteSucive((Nacional) usr);
    		//ACTUALIZAE USR EN BD
    		usr.setClienteTelepeaje(cliTelepeaje);
    		return cliTelepeaje; 
        
    	}else {
    		//se actualiza al asosiar(cli, tarjeta)
    		ClienteTelepeaje cliTelepeaje = new ClienteTelepeaje(usr, null, null);
    		//ACTUALIZAE USR EN BD
    		usr.setClienteTelepeaje(cliTelepeaje);
    		return cliTelepeaje; 
    	}
    	
    }
    
    @Override
    public void crearClienteSucive(Nacional usr) {
    	ClienteSucive cliSucive = new ClienteSucive(usr);
    	//ACTUALIZAE USR EN BD
    	usr.setClienteSucive(cliSucive);
    }
    
    public void vicularUsuarioVehiculo(Usuario usr, Vehiculo vehiculo) {
    	LocalDateTime ahora = LocalDateTime.now();
    	Vinculo v = new Vinculo(ahora, true, vehiculo);
    	
    	List<Vinculo> listaVinculos = usr.getVehiculosVinculados();
    	if ( listaVinculos == null) {
    		listaVinculos = new ArrayList<>();	
    	}
    	
    	listaVinculos.add(v);
    	
    }
    
    public List<Vehiculo> findVehiculoByUser(Usuario usr) {
    	List<Vehiculo> vehiculos = new ArrayList<>();
    	for(Vinculo vinculos: usr.getVehiculosVinculados()) {
    		if(vinculos.isActivo() == true) {
    			vehiculos.add(vinculos.getVehiculo());
    		}
    	}
    	
    	return vehiculos;
    }
}
