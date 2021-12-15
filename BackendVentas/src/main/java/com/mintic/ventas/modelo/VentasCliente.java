package com.mintic.ventas.modelo;

import org.springframework.data.annotation.Id;

public class VentasCliente {
	@Id
	private String cedula;
	private String nombre;
	private Double total;
	public VentasCliente(String cedula, String nombre, Double total) {
		super();
		this.cedula = cedula;
		this.nombre = nombre;
		this.total = total;
	}
	public VentasCliente() {
		super();
	}
	public String getCedula() {
		return cedula;
	}
	public void setCedula(String cedula) {
		this.cedula = cedula;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Double getTotal() {
		return total;
	}
	public void setTotal(Double total) {
		this.total = total;
	}
	
}
