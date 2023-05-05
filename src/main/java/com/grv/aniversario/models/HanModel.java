package com.grv.aniversario.models;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="hanesxregion")
public class HanModel {

	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)    
    @Column(nullable = false)
	private Long idHan;
	private String han;
	private Integer obsolet;
	
	@ManyToOne
	@JoinColumn(name = "idCoordinadora", referencedColumnName = "idCoordinadora")
	private CoordinadoraModel coordinadora;
	
	@ManyToOne
	@JoinColumn(name = "idRegion", referencedColumnName = "idRegion")
	private RegionModel region;

	private String subRegion;
	
	@ManyToOne
	@JoinColumn(name = "idComunidad", referencedColumnName = "idComunidad")
	private ComunidadModel comunidad;
	
	@ManyToOne
	@JoinColumn(name = "idBarrio", referencedColumnName = "idBarrio")
	private BarrioModel barrio;
	
	private String sector;
	
	private LocalDateTime fecha;

	public Long getIdHan() {
		return idHan;
	}

	public void setIdHan(Long idHan) {
		this.idHan = idHan;
	}

	public String getHan() {
		return han;
	}

	public void setHan(String han) {
		this.han = han;
	}

	public Integer getObsolet() {
		return obsolet;
	}

	public void setObsolet(Integer obsolet) {
		this.obsolet = obsolet;
	}

	public CoordinadoraModel getCoordinadora() {
		return coordinadora;
	}

	public void setCoordinadora(CoordinadoraModel coordinadora) {
		this.coordinadora = coordinadora;
	}

	public RegionModel getRegion() {
		return region;
	}

	public void setRegion(RegionModel region) {
		this.region = region;
	}

	public String getSubRegion() {
		return subRegion;
	}

	public void setSubRegion(String subRegion) {
		this.subRegion = subRegion;
	}



	public ComunidadModel getComunidad() {
		return comunidad;
	}

	public void setComunidad(ComunidadModel comunidad) {
		this.comunidad = comunidad;
	}

	public BarrioModel getBarrio() {
		return barrio;
	}

	public void setBarrio(BarrioModel barrio) {
		this.barrio = barrio;
	}

	public String getSector() {
		return sector;
	}

	public void setSector(String sector) {
		this.sector = sector;
	}

	public LocalDateTime getFecha() {
		return fecha;
	}

	public void setFecha(LocalDateTime fecha) {
		this.fecha = fecha;
	}
	
	
	
}
