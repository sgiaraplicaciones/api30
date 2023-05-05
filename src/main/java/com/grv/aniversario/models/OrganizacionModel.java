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
@Table(name="organizacion")
public class OrganizacionModel {

	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)    
    @Column(name="ID_ORGANIZACION", nullable = false)
	private Long idOrganizacion;
	
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
	
	@ManyToOne
	@JoinColumn(name = "idHan", referencedColumnName = "idHan")
	private HanModel idHan;

	public Long getIdOrganizacion() {
		return idOrganizacion;
	}

	public void setIdOrganizacion(Long idOrganizacion) {
		this.idOrganizacion = idOrganizacion;
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

	public HanModel getIdHan() {
		return idHan;
	}

	public void setIdHan(HanModel idHan) {
		this.idHan = idHan;
	}


	
	
}
