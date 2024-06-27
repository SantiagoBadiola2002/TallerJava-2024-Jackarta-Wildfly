package org.tallerjava.moduloMonitoreo.infraestructura;

import io.micrometer.core.instrument.Clock;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.influx.InfluxConfig;
import io.micrometer.influx.InfluxMeterRegistry;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.RequestScoped;

import java.time.Duration;

@ApplicationScoped
public class RegistradorDeMetricas {
    public static final String PEAJE_COUNTER_VEHICULO_NO_ENCONTRADO ="peajeERRORVehiculoNoEncontrado";
    public static final String PEAJE_COUNTER_PAGO_EXTRANJERO ="peajeOKPagoRealizadoExtranjero";
    public static final String PEAJE_COUNTER_PAGO_NACIONAL ="peajeOKPagoRealizadoNacional";
    public static final String PEAJE_COUNTER_PAGO_SUCIVE ="peajeOKPagoSucive";
    
    public static final String GESTION_COUNTER_PRE_PAGO ="gestionOKPrePago";
    public static final String GESTION_COUNTER_POST_PAGO ="gestionOKPostPago";
    public static final String GESTION_COUNTER_SALDO_INSUFICIENTE ="gestionERRORSaldoInsuficiente";
    public static final String GESTION_COUNTER_TARJETA_RECHAZADA ="gestionERRORTarjetaRechazada";
    public static final String GESTION_COUNTER_VEHICULO_NO_ENCONTRADO ="gestionERRORVehiculoTagNoEncontrado";
    public static final String GESTION_COUNTER_CLIENTE_NO_ENCONTRADO ="gestionERRORClienteTelepeajeNoEncontrado";
    
    public static final String MEDIOSPAGO_COUNTER_COBRO_TARJETA ="mediospagoOKCobroTarjeta";
    public static final String MEDIOSPAGO_COUNTER_COBRO_TARJETA_RECHAZADO ="mediospagoERRORCobroTarjetaRechazado";
    public static final String MEDIOSPAGO_COUNTER_FALLO_PROCESAR_PAGO ="mediospagoERRORAlProcesarPago";

    public static final String SUCIVE_COUNTER_COBRO_SUCIVE ="suciveOKCobroSucive";
    
    
    
    private InfluxConfig config;

    @PostConstruct
    public void init() {
        //configuración del repositorio de metricas (influxdb)
        config = new InfluxConfig() {
            @Override
            public String get(String s) {
                return null;
            }

            @Override
            public Duration step() {
                return Duration.ofSeconds(10);
            }

            @Override
            public String db() {
                return "metricasTallerJava";
            }
        };


    }

    public void incrementarCounter(String nombreCounter) {
        MeterRegistry meterRegistry;
        meterRegistry = new InfluxMeterRegistry(config, Clock.SYSTEM);
        meterRegistry.counter(nombreCounter).increment();
    }
}
