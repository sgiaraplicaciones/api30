package com.grv.aniversario.DTO;

import java.time.LocalDateTime;

public class RequestCountDTO {
	
	private LocalDateTime desde;
	private LocalDateTime hasta;
	
	public LocalDateTime getDesde() {
		return desde;
	}
	public void setDesde(LocalDateTime desde) {
		this.desde = desde;
	}
	public LocalDateTime getHasta() {
		return hasta;
	}
	public void setHasta(LocalDateTime hasta) {
		this.hasta = hasta;
	}
	
	
}
