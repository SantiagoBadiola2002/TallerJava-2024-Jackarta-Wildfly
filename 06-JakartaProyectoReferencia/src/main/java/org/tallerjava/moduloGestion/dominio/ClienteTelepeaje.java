package org.tallerjava.moduloGestion.dominio;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ClienteTelepeaje{
	
    public ClienteTelepeaje(PrePaga prePaga, Object object) {
    	
		this.ctaPrepaga = prePaga;
		this.ctaPostPaga = (PostPaga) object;
	}
	private PrePaga ctaPrepaga;
    private PostPaga ctaPostPaga;
    
    
	public PrePaga getCtaPrepaga() {
		return this.ctaPrepaga;
	}
}
