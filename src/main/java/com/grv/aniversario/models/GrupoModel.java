package com.grv.aniversario.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="grupos")
public class GrupoModel {

	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)    
    @Column(name="ID_GRUPO", nullable = false)
	private Long idGrupo;
	
	private String nombre;
	
	private Integer estado;

	public Long getIdGrupo() {
		return idGrupo;
	}

	public void setIdGrupo(Long idGrupo) {
		this.idGrupo = idGrupo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Integer getEstado() {
		return estado;
	}

	public void setEstado(Integer estado) {
		this.estado = estado;
	}
	
	
}
