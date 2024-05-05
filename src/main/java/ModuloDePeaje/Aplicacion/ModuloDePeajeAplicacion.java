package ModuloDePeaje.Aplicacion;

public interface ModuloDePeajeAplicacion {
	
	public boolean estaHabilitado(Object identificador);
	public void actualizarTarifaComun(double importe);
	public void actualizarTarifaPreferencial(double importe);

}
