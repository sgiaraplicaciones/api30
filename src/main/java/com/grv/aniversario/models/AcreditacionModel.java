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
@Table(name="acreditacion")
public class AcreditacionModel {

	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)    
    @Column(name="ID_ACREDITACION", nullable = false)
	private Long idAcreditacion;
	
	@ManyToOne
	@JoinColumn(name = "ID_MIEMBRO", referencedColumnName = "ID_MIEMBRO")
	private MiembroModel miembro;
	
	@Column(name="FECHA_HORA_INGRESO", nullable = false)
	private LocalDateTime fechaHoraIngreso;

	public Long getIdAcreditacion() {
		return idAcreditacion;
	}

	public void setIdAcreditacion(Long idAcreditacion) {
		this.idAcreditacion = idAcreditacion;
	}

	public MiembroModel getMiembro() {
		return miembro;
	}

	public void setMiembro(MiembroModel miembro) {
		this.miembro = miembro;
	}

	public LocalDateTime getFechaHoraIngreso() {
		return fechaHoraIngreso;
	}

	public void setFechaHoraIngreso(LocalDateTime fechaHoraIngreso) {
		this.fechaHoraIngreso = fechaHoraIngreso;
	}
	
	
}
