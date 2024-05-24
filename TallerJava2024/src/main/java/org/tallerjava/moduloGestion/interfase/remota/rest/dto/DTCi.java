package org.tallerjava.moduloGestion.interfase.remota.rest.dto;

import lombok.Data;

@Data
public class DTCi {
	
	long ci;
	
	public DTCi() {
		
	}

	public DTCi(long ci) {
		this.ci = ci;
	}

	public long getCi() {
		return ci;
	}

}
