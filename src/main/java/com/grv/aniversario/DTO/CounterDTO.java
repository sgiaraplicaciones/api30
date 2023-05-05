package com.grv.aniversario.DTO;

import com.grv.aniversario.models.GrupoModel;

public class CounterDTO {

	private Integer total;
	private GrupoModel grupo;
	
	public Integer getTotal() {
		return total;
	}
	public void setTotal(Integer total) {
		this.total = total;
	}
	public GrupoModel getGrupo() {
		return grupo;
	}
	public void setGrupo(GrupoModel grupo) {
		this.grupo = grupo;
	}
	
	
}
