package org.tallerjava.moduloGestion.aplicacion;

import org.tallerjava.moduloGestion.dominio.ClienteTelepeaje;
import org.tallerjava.moduloGestion.dominio.Cuenta;
import org.tallerjava.moduloGestion.dominio.Usuario;
import org.tallerjava.moduloGestion.dominio.Vehiculo;

import java.util.List;

public interface ServicioPago {
    public boolean realizarPrePago(int tag, double importe);
    public boolean realizarPostPago(int tag, double importe);

    public boolean esClienteTelepeaje(int tag);
    
    public void altaClienteTelepeaje(Usuario usr);
    
    public boolean vincularVehiculo(ClienteTelepeaje cliTelepeaje, Vehiculo vehiculo);
}
