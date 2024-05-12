package ModuloDeClases;

import java.util.Date;

import jakarta.inject.Inject;

public class PasadaPorPeaje {
	
    private Date fecha;
    private double costo;
    
    @Inject
    private DataTipoCobro tipoCobro;

    public PasadaPorPeaje(Date fecha, double costo, DataTipoCobro tipoCobro) {
        this.fecha = fecha;
        this.costo = costo;
        this.tipoCobro = tipoCobro;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public double getCosto() {
        return costo;
    }

    public void setCosto(double costo) {
        this.costo = costo;
    }

    public DataTipoCobro getTipoCobro() {
        return tipoCobro;
    }

    public void setTipoCobro(DataTipoCobro tipoCobro) {
        this.tipoCobro = tipoCobro;
    }

    @Override
    public String toString() {
        return "PasadaPorPeaje{" +
                "fecha=" + fecha +
                ", costo=" + costo +
                ", tipoCobro=" + tipoCobro +
                '}';
    }
}
