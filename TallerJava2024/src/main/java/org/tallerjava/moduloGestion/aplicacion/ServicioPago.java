package org.tallerjava.moduloGestion.aplicacion;

import org.tallerjava.moduloGestion.dominio.*;
import org.tallerjava.moduloGestion.interfase.remota.rest.dto.*;

import java.time.LocalDateTime;
import java.util.List;

public interface ServicioPago {
	
    public boolean realizarPrePago(int tag, double importe);
    
    public boolean realizarPostPago(int tag, double importe);

    public boolean esClienteTelepeaje(int tag);
    
    public boolean altaClienteTelepeaje(Usuario usr);
    
    public boolean vincularVehiculo(int idCliente, int tag, String matricula);
    
    public boolean desvincularVehiculo(int idCliente, int tag, String matricula);
    
    public List<Integer> obtenerCuentasPorTag(int tag);
    
	public boolean agregarTarjeta(int idCliente, int nroTarjeta, LocalDateTime fechaVtoTarjeta, String nombreCompletoUsuario);

    public List<PasadasPorPeaje> consultarPasadas(int idCliente, LocalDateTime fechaInicial, LocalDateTime fechaFinal);
    
    public List<PasadasPorPeaje> consultarPasadas(int idCliente, int tag, String matricula, LocalDateTime fechaInicial, LocalDateTime fechaFinal);

    public double cargarSaldo(int idCliente, double importe);
    
    public double consultarSaldo(int idCliente);

	public List<DTVehiculo>  mostrarVehiculoVinculados(int id);
}
