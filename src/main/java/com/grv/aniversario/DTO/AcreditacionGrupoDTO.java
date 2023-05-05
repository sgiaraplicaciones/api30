package com.grv.aniversario.DTO;

public class AcreditacionGrupoDTO {
	
	private Integer dni;
	private Long idGrupo;
	private LocalidadDTO localidad;
	private Integer fichaMyo;
	
	public Integer getDni() {
		return dni;
	}
	public void setDni(Integer dni) {
		this.dni = dni;
	}
	public Long getIdGrupo() {
		return idGrupo;
	}
	public void setIdGrupo(Long idGrupo) {
		this.idGrupo = idGrupo;
	}
	public LocalidadDTO getLocalidad() {
		return localidad;
	}
	public void setLocalidad(LocalidadDTO localidad) {
		this.localidad = localidad;
	}
	public Integer getFichaMyo() {
		return fichaMyo;
	}
	public void setFichaMyo(Integer fichaMyo) {
		this.fichaMyo = fichaMyo;
	}
	
	
}
