package com.grv.aniversario.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="data_actualizada")
public class DataActualizadaModel {

	@Id
    @Column(name="DNI", nullable = false, unique = true)
	private Integer dni;
	
	@Column(name="ID_LOCALIDAD")
	private Long idLocalidad;
	
	@Column(name="ID_GRUPO")
	private Long idGrupo;
	
	@Column(name="FICHA_MYO")
	private Integer fichaMyo;

	public Integer getDni() {
		return dni;
	}

	public void setDni(Integer dni) {
		this.dni = dni;
	}

	public Long getIdLocalidad() {
		return idLocalidad;
	}

	public void setIdLocalidad(Long idLocalidad) {
		this.idLocalidad = idLocalidad;
	}

	public Long getIdGrupo() {
		return idGrupo;
	}

	public void setIdGrupo(Long idGrupo) {
		this.idGrupo = idGrupo;
	}

	public Integer getFichaMyo() {
		return fichaMyo;
	}

	public void setFichaMyo(Integer fichaMyo) {
		this.fichaMyo = fichaMyo;
	}
	
	
}
