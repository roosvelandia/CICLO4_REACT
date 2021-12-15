package com.mintic.ventas.modelo;

import org.springframework.data.annotation.Id;

public class VentasSucursal {
	@Id
	private String ciudad;
	private double total;
	public String getCiudad() {
		return ciudad;
	}
	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	public VentasSucursal(String ciudad, double total) {
		super();
		this.ciudad = ciudad;
		this.total = total;
	}
	public VentasSucursal() {
		super();
	}
}
