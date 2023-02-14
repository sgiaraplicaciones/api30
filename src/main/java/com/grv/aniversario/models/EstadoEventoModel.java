package com.grv.aniversario.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "estados_evento")
public class EstadoEventoModel {

	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)    
    @Column(name="ID_ESTADO_EVENTO", nullable = false)
    private Long idEstadoEvento;
	
	private String descripcion;

	public Long getIdEstadoEvento() {
		return idEstadoEvento;
	}

	public void setIdEstadoEvento(Long idEstadoEvento) {
		this.idEstadoEvento = idEstadoEvento;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	
	
}
