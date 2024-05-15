package org.tallerjava.moduloGestion.infraestructura.persistencia;

import org.tallerjava.moduloGestion.dominio.*;
import org.tallerjava.moduloGestion.dominio.repo.UsuarioRepositorio;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class UsuarioRepositorioImpl implements UsuarioRepositorio {
    @Override
    public Usuario findByTag(int tag) {
   	    LocalDateTime date = LocalDateTime.parse("2024-05-14T10:30:00");
        PrePaga prePaga = new PrePaga(8676, 9678, date,1000);
        ClienteTelepeaje cliTelepeaje = new ClienteTelepeaje(null, prePaga, null);
        List<Vinculo> listVinculos= new ArrayList<>();
        Identificador identificador = new Identificador(1, "BAA 2222", 2001);
        
        LocalDateTime ahora = LocalDateTime.now();
        
        listVinculos.add(new Vinculo(ahora, true, new Vehiculo(1, identificador, cliTelepeaje)));
        
        Usuario usuario = new
                Nacional(1, "pepe","pepe@gmail.com",listVinculos, cliTelepeaje);

        return usuario;
    }

    @Override
    public List<Cuenta> findCuentasByTag(int tag) {
    	 LocalDateTime date = LocalDateTime.parse("2024-05-14T10:30:00");
        List<Cuenta> listCuentas = new ArrayList<>();
        listCuentas.add(new PrePaga(3453, 3452, date,100));
        listCuentas.add(new PostPaga(3123, 3213, date, 
                new Tarjeta(1,2222,null, "usuario tarjeta")));
        return listCuentas;
    }
    @Override
    public ClienteTelepeaje crearClienteTelepeaje(Usuario usr) {
    	//incio saldo prePaga en 0 sin tarjeta
    	LocalDateTime ahora = LocalDateTime.now();

    	if (usr.soyNacional()) {
    		PrePaga prePaga = new PrePaga(1, 1234, ahora, 0);
    		ClienteTelepeaje cliTelepeaje = new ClienteTelepeaje(null, prePaga, null);
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
    
    public Vehiculo findVehiculoByUser(Usuario usr) {
    	 LocalDateTime date = LocalDateTime.parse("2024-05-14T10:30:00");
    	 Tarjeta tarjeta = new Tarjeta(2, 213, date, "JuanchoSuarez");
         PostPaga postPago = new PostPaga(33, 3423, date, tarjeta);
         ClienteTelepeaje cliTelepeaje = new ClienteTelepeaje(usr, null, postPago);
         Identificador identificador = new Identificador(8, "BAV 3337", 2002);
    	 Vehiculo vehiculo = new Vehiculo(2, identificador, cliTelepeaje);
    	
    	return vehiculo;
    }
}
