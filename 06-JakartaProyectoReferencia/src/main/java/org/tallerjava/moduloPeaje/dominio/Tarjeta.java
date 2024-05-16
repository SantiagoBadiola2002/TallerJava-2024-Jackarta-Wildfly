package org.tallerjava.moduloPeaje.dominio;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class Tarjeta {
    private long id;
    private int nro;
    private LocalDateTime fechaVto;
    private String nombreCompletoUsuario;
    
	public Tarjeta(long id, int nro, LocalDateTime fechaVto, String nombreCompletoUsuario) {
		this.id = id;
		this.nro = nro;
		this.fechaVto = fechaVto;
		this.nombreCompletoUsuario = nombreCompletoUsuario;
	}
    
    
}