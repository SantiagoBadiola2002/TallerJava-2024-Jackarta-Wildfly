package org.tallerjava.moduloGestion.interfase.remota.rest.dto;

import lombok.Data;

@Data
public class DTTag {
	
	private int tag;
	
	public DTTag() {
		
	}

	public int getTag() {
		return tag;
	}

}
