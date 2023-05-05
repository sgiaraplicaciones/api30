package com.grv.aniversario.DTO;

public class RequestAcreditacionExtranetDTO {

	private Integer id;
	private String tipo;
	private String nombre;
	private String localidad;
	private Long id_localidad;
	private String cod_ubicacion;
	private Integer id_ubicacion;
	private String id_actividad;
	private String comentarios;
	private Long id_grupo;
	private String token;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getLocalidad() {
		return localidad;
	}
	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}
	public Long getId_localidad() {
		return id_localidad;
	}
	public void setId_localidad(Long id_localidad) {
		this.id_localidad = id_localidad;
	}
	public String getCod_ubicacion() {
		return cod_ubicacion;
	}
	public void setCod_ubicacion(String cod_ubicacion) {
		this.cod_ubicacion = cod_ubicacion;
	}
	public Integer getId_ubicacion() {
		return id_ubicacion;
	}
	public void setId_ubicacion(Integer id_ubicacion) {
		this.id_ubicacion = id_ubicacion;
	}
	public String getId_actividad() {
		return id_actividad;
	}
	public void setId_actividad(String id_actividad) {
		this.id_actividad = id_actividad;
	}
	public String getComentarios() {
		return comentarios;
	}
	public void setComentarios(String comentarios) {
		this.comentarios = comentarios;
	}
	public Long getId_grupo() {
		return id_grupo;
	}
	public void setId_grupo(Long id_grupo) {
		this.id_grupo = id_grupo;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	
}
