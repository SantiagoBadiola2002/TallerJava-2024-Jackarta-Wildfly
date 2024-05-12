package ModuloDeSucive.Aplicacion;

import java.util.Date;
import java.util.List;

import ModuloDeClases.Matricula;

public interface ModuloDeSuciveAplicacion {
	
	public void notificarPago(Matricula matricula, double importe);
	//public List<Pagos> consultaDePagos(Date fechaInicial, Date fecha);
    //public Pagos consultaDePagos(Matricula matricula);
}

