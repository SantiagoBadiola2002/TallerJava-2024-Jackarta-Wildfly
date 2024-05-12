package org.tallerjava.moduloGestion.infraestructura.persistencia;

import org.tallerjava.moduloGestion.dominio.*;
import org.tallerjava.moduloGestion.dominio.repo.UsuarioRepositorio;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class UsuarioRepositorioImpl implements UsuarioRepositorio {
    @Override
    public Usuario findByTag(int tag) {
        PrePaga prePaga = new PrePaga(1000);
        ClienteTelepeaje cliTelepeaje = new ClienteTelepeaje(prePaga, null);
        List<Vinculo> listVinculos= new ArrayList<>();
        Identificador identificador = new Identificador(1, "BAA 2222", 2001);
        
        @SuppressWarnings("deprecation")
		Date fechaManual = new Date(124, 4, 7);
        
        listVinculos.add(new Vinculo(fechaManual, true, new Vehiculo(1, identificador, cliTelepeaje)));
        
        Usuario usuario = new
                Nacional(1, "pepe","pepe@gmail.com",listVinculos, cliTelepeaje);

        return usuario;
    }

    @Override
    public List<Cuenta> findCuentasByTag(int tag) {
        List<Cuenta> listCuentas = new ArrayList<>();
        listCuentas.add(new PrePaga(100));
        listCuentas.add(new PostPaga(
                new Tarjeta(1,2222,null, "usuario tarjeta")));
        return listCuentas;
    }
}
