package com.grv.aniversario.models;

import java.time.LocalDateTime;

import javax.persistence.*;

@Entity
@Table(name="miembros")
public class MiembroModel {

	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)    
    @Column(name="ID_MIEMBRO", nullable = false)
	private Long idMiembro;
	
	private String nombre;
	private String apellido;
	private String dni;
	private String mail;

	@ManyToOne
	@JoinColumn(name = "ID_ORGANIZACION", referencedColumnName = "ID_ORGANIZACION")
	private OrganizacionModel organizacion;
	
	@ManyToOne
	@JoinColumn(name = "ID_DEPARTAMENTO", referencedColumnName = "ID_DEPARTAMENTO")
	private DepartamentoModel departamento;
	
	@Column(name="FECHA_MODIFICACION")
	private LocalDateTime fechaModificacion;
	
	private transient boolean acreditade;
	
	public boolean isAcreditade() {
		return acreditade;
	}

	public void setAcreditade(boolean acreditade) {
		this.acreditade = acreditade;
	}

	public Long getIdMiembro() {
		return idMiembro;
	}

	public void setIdMiembro(Long idMiembro) {
		this.idMiembro = idMiembro;
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

	public OrganizacionModel getOrganizacion() {
		return organizacion;
	}

	public void setOrganizacion(OrganizacionModel organizacion) {
		this.organizacion = organizacion;
	}

	public DepartamentoModel getDepartamento() {
		return departamento;
	}

	public void setDepartamento(DepartamentoModel departamento) {
		this.departamento = departamento;
	}

	public LocalDateTime getFechaModificacion() {
		return fechaModificacion;
	}

	public void setFechaModificacion(LocalDateTime fechaModificacion) {
		this.fechaModificacion = LocalDateTime.now();
	}
	
	
}
