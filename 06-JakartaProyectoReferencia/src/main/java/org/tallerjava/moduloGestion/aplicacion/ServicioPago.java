package org.tallerjava.moduloGestion.aplicacion;

import org.tallerjava.moduloGestion.dominio.*;


import java.time.LocalDateTime;
import java.util.List;

public interface ServicioPago {
    public boolean realizarPrePago(int tag, double importe);
    public boolean realizarPostPago(int tag, double importe);

    public boolean esClienteTelepeaje(int tag);
    
    public void altaClienteTelepeaje(Usuario usr);
    
    public boolean vincularVehiculo(ClienteTelepeaje cliTelepeaje, Vehiculo vehiculo);
    
    public List<Integer> obtenerCuentasPorTag(int tag);
    
	public void agregarTarjeta(long ci, int nroTarjeta, LocalDateTime fechaVtoTarjeta, String nombreCompletoUsuario);

    public List<PasadasPorPeaje> consultarPasadas(long ci, LocalDateTime fechaInicial, LocalDateTime fechaFinal);
    
    public List<PasadasPorPeaje> consultarPasadas(long ci, int tag, String matricula, LocalDateTime fechaInicial, LocalDateTime fechaFinal);


}
