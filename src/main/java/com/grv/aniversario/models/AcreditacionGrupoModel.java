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
@Table(name="AcreditacionGrupos")
public class AcreditacionGrupoModel {
	
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)    
    @Column(name="ID_ACREDITACION", nullable = false)
	private Long idAcreditacion;
	
	private String dni;
	private String nombre;
	private String apellido;
	private String region;
	private String coordinadora;
	private String comunidad;
	private String barrio;
	private String sector;
	private Integer miembro;
	
	@ManyToOne
	@JoinColumn(name = "ID_GRUPO", referencedColumnName = "ID_GRUPO")
	private GrupoModel grupo;
	
	private Integer fichamyo;
	
	@Column(name="FECHA_HORA_INGRESO", nullable = false)
	private LocalDateTime fechaHoraIngreso;
	
	
	public Long getIdAcreditacion() {
		return idAcreditacion;
	}
	public void setIdAcreditacion(Long idAcreditacion) {
		this.idAcreditacion = idAcreditacion;
	}
	public String getDni() {
		return dni;
	}
	public void setDni(String dni) {
		this.dni = dni;
	}
	public GrupoModel getGrupo() {
		return grupo;
	}
	public void setGrupo(GrupoModel grupo) {
		this.grupo = grupo;
	}
	public LocalDateTime getFechaHoraIngreso() {
		return fechaHoraIngreso;
	}
	public void setFechaHoraIngreso(LocalDateTime fechaHoraIngreso) {
		this.fechaHoraIngreso = fechaHoraIngreso;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	public String getCoordinadora() {
		return coordinadora;
	}
	public void setCoordinadora(String coordinadora) {
		this.coordinadora = coordinadora;
	}
	public Integer getFichamyo() {
		return fichamyo;
	}
	public void setFichamyo(Integer fichamyo) {
		this.fichamyo = fichamyo;
	}
	public String getComunidad() {
		return comunidad;
	}
	public void setComunidad(String comunidad) {
		this.comunidad = comunidad;
	}
	public String getBarrio() {
		return barrio;
	}
	public void setBarrio(String barrio) {
		this.barrio = barrio;
	}
	public String getSector() {
		return sector;
	}
	public void setSector(String sector) {
		this.sector = sector;
	}
	public Integer getMiembro() {
		return miembro;
	}
	public void setMiembro(Integer miembro) {
		this.miembro = miembro;
	}
	
	
}
