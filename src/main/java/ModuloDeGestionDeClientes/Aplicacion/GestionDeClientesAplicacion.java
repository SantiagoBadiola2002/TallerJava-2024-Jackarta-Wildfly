package ModuloDeGestionDeClientes.Aplicacion;

import java.util.Date;
import java.util.List;

import ModuloDeClases.ClienteTelepeaje;
import ModuloDeClases.PasadaPorPeaje;
import ModuloDeClases.Tarjeta;
import ModuloDeClases.Usuario;
import ModuloDeClases.Vehiculo;

public interface GestionDeClientesAplicacion {
	
	public void altaClienteTelepeaje(ClienteTelepeaje usuario); //registra a un usuario como cliente Telepeaje
	public void cargarSaldo(ClienteTelepeaje usuario, Double importe); //cargaSaldo a la cuenta PRE paga del cliente
	public double consultaSaldo(ClienteTelepeaje usuario); //devuelve el saldo actual de la cuenta PRE paga del cliente 
    // public Cuenta obtenerCuentasPorTag(Tag tag); //devuelve los tipos de cuentas asociadas al cliente Telepeaje, Si la cuenta es de PrePago, devuelve el saldo actual *Utilizado por el módulo de Peaje*.
	public void vincularVehiculo(Vehiculo vehiculo, ClienteTelepeaje usuario); // CLiente Sucive, Telepeaje o Usuario?
	public void desvincularVehiculo(Vehiculo vehiculo, ClienteTelepeaje usuario);
	public List <Vehiculo> mostrarVehiculosVinculados(ClienteTelepeaje usuario);
	public double asociarTarjeta(Usuario usuario, Tarjeta tarjeta); //asocia tarjeta de crédito a la cuenta POST paga del cliente
	public List<PasadaPorPeaje> consultarPasadas(ClienteTelepeaje usuario, Date fechaInicio, Date fechaFin); //devuelve las pasadas realizadas por todos los vehículos registrados en un rango de fechas.
	public List<PasadaPorPeaje> consultarPasadas(ClienteTelepeaje usuario, Vehiculo vehiculo, Date fechaInicio, Date fechaFin); //idem anterior, pero solo para un vehículo
	public void realizarPrePago(double importe); //descuenta el importe del pago al saldo del cliente. *Utilizado por el módulo de Peaje*.
    public void realizarPostPago(double importe); //realiza el pago utilizando tarjeta de crédito

}
