package com.grv.aniversario.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="region")
public class RegionModel {

	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)    
    @Column(nullable = false)
	private Long idRegion;
	
	@ManyToOne
	@JoinColumn(name = "idCoordinadora", referencedColumnName = "idCoordinadora")
	private CoordinadoraModel coordinadora;
	
	private String descripcion;
	private Integer activo;
	private Integer cantidad;
	
	public Long getIdRegion() {
		return idRegion;
	}
	public void setIdRegion(Long idRegion) {
		this.idRegion = idRegion;
	}

	public CoordinadoraModel getCoordinadora() {
		return coordinadora;
	}
	public void setCoordinadora(CoordinadoraModel coordinadora) {
		this.coordinadora = coordinadora;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public Integer getActivo() {
		return activo;
	}
	public void setActivo(Integer activo) {
		this.activo = activo;
	}
	public Integer getCantidad() {
		return cantidad;
	}
	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}
	
	
	
}
