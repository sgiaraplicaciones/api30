package com.grv.aniversario.DTO;

public class ImgResponseDTO {
	
	private Long idImagen;
	private Long idDenuncia;
	private String descripcion;
	private String fileName;
	private String fileBase64;
	private String titulo;
	
	
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public Long getIdDenuncia() {
		return idDenuncia;
	}
	public void setIdDenuncia(Long idDenuncia) {
		this.idDenuncia = idDenuncia;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getFileBase64() {
		return fileBase64;
	}
	public void setFileBase64(String fileBase64) {
		this.fileBase64 = fileBase64;
	}
	public Long getIdImagen() {
		return idImagen;
	}
	public void setIdImagen(Long idImagen) {
		this.idImagen = idImagen;
	}
	
}
