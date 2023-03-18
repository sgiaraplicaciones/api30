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
@Table(name="tickets")
public class TicketModel {

	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)    
    @Column(nullable = false)
    private Long id;
	
	private String nombre;
	private String apellido;
	private String dni;
	private String mail;
	private String celular;
	private int verificado; //1=acreditado
	@Column(name="FECHA_GENERADO")
	private LocalDateTime fechaGenerado;
	@Column(name="FECHA_ACREDITADO")
	private LocalDateTime fechaAcreditado;
	private int esMiembro;
	private String region;
	private String partidoComunidad;
	private String localidadBarrio;
	private String han;
	@Column(name="FECHA_NACIMIENTO")
	private String fechaNacimiento;
	
	
//	@Column(name="CODIGO_QR")
//	private String codigoQR;
	
	public String getFechaNacimiento() {
		return fechaNacimiento;
	}
	public void setFechaNacimiento(String fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
	@ManyToOne
	@JoinColumn(name = "ID_EVENTO", referencedColumnName = "ID")
	private EventoModel evento;
	
//	@Column(name="UID_FIREBASE")
//	private Long uidFirebase;
	

	public EventoModel getEvento() {
		return evento;
	}
	public void setEvento(EventoModel evento) {
		this.evento = evento;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
	public String getDni() {
		return dni;
	}
	public void setDni(String dni) {
		this.dni = dni;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public String getCelular() {
		return celular;
	}
	public void setCelular(String celular) {
		this.celular = celular;
	}
	public int getVerificado() {
		return verificado;
	}
	public void setVerificado(int verificado) {
		this.verificado = verificado;
	}
	public LocalDateTime getFechaGenerado() {
		return fechaGenerado;
	}
	public void setFechaGenerado(LocalDateTime fechaGenerado) {
		this.fechaGenerado = fechaGenerado;
	}
	public LocalDateTime getFechaAcreditado() {
		return fechaAcreditado;
	}
	public void setFechaAcreditado(LocalDateTime fechaAcreditado) {
		this.fechaAcreditado = fechaAcreditado;
	}
	public int getEsMiembro() {
		return esMiembro;
	}
	public void setEsMiembro(int esMiembro) {
		this.esMiembro = esMiembro;
	}
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	public String getPartidoComunidad() {
		return partidoComunidad;
	}
	public void setPartidoComunidad(String partidoComunidad) {
		this.partidoComunidad = partidoComunidad;
	}
	public String getLocalidadBarrio() {
		return localidadBarrio;
	}
	public void setLocalidadBarrio(String localidadBarrio) {
		this.localidadBarrio = localidadBarrio;
	}
	public String getHan() {
		return han;
	}
	public void setHan(String han) {
		this.han = han;
	}
	
	
	
}
