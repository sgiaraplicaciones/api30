package com.grv.aniversario.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="coordinadora")
public class CoordinadoraModel {

	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)    
    @Column(nullable = false)
	private Long idCoordinadora;
	private String descripcion;
	private String iniciales;
	private Integer activo;
	private Integer cantidad;
	
	

	public Long getIdCoordinadora() {
		return idCoordinadora;
	}
	public void setIdCoordinadora(Long idCoordinadora) {
		this.idCoordinadora = idCoordinadora;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getIniciales() {
		return iniciales;
	}
	public void setIniciales(String iniciales) {
		this.iniciales = iniciales;
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
