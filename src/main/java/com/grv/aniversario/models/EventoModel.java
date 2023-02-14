package com.grv.aniversario.models;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.*;

@Entity
@Table(name = "eventos")
public class EventoModel {
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)    
    @Column(nullable = false)
    private Long id;

    private String nombre;
    
    @Column(name="DIA_EVENTO")
    private LocalDate diaEvento;
    
    @Column(name="HORA_EVENTO")
    private LocalTime horaEvento;
    
    @ManyToOne
	@JoinColumn(name = "ID_ESTADO", referencedColumnName = "ID_ESTADO_EVENTO")
    private EstadoEventoModel estado;
    
    @Column(name="CAPACIDAD_MAXIMA")
    private Integer capacidadMaxima;
 
    
    
    
//    public EventoModel(String nombre, LocalDate diaEvento, LocalTime horaEvento, EstadoEventoModel estado,
//			Integer capacidadMaxima) {
//		super();
//		this.nombre = nombre;
//		this.diaEvento = diaEvento;
//		this.horaEvento = horaEvento;
//		this.estado = estado;
//		this.capacidadMaxima = capacidadMaxima;
//	}

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

	public EstadoEventoModel getEstado() {
		return estado;
	}

	public void setEstado(EstadoEventoModel estado) {
		this.estado = estado;
	}

	public Integer getCapacidadMaxima() {
		return capacidadMaxima;
	}

	public void setCapacidadMaxima(Integer capacidadMaxima) {
		this.capacidadMaxima = capacidadMaxima;
	}

	public LocalDate getDiaEvento() {
		return diaEvento;
	}

	public void setDiaEvento(LocalDate diaEvento) {
		this.diaEvento = diaEvento;
	}

	public LocalTime getHoraEvento() {
		return horaEvento;
	}

	public void setHoraEvento(LocalTime horaEvento) {
		this.horaEvento = horaEvento;
	}
	
	

	
}
