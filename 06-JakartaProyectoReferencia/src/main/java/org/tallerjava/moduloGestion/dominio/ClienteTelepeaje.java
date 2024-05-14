package org.tallerjava.moduloGestion.dominio;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ClienteTelepeaje{
	
	private PrePaga ctaPrepaga;
    private PostPaga ctaPostPaga;
	
    public ClienteTelepeaje(PrePaga prePaga, PostPaga postPaga) {
    	
		this.ctaPrepaga = prePaga;
		this.ctaPostPaga = postPaga;
	}
    
	public PrePaga getCtaPrepaga() {
		return this.ctaPrepaga;
	}
}
