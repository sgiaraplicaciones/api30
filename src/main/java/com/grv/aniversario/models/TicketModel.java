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
	
//	@Column(name="CODIGO_QR")
//	private String codigoQR;
	
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
}
