package com.grv.aniversario.models;

import javax.persistence.*;

@Entity
@Table(name = "eventos")
public class EventoModel {
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)    
    @Column(nullable = false)
    private Long id;

    private String nombre;
    
    private Long estado;
 
    
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

	public void setEstado(Long estado) {
        this.estado = estado;
    }

    public Long getEstado() {
        return estado;
    }
}
