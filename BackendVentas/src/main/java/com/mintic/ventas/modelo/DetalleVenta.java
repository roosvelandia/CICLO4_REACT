package com.mintic.ventas.modelo;

public class DetalleVenta {

	private int cantidad_producto;
	private int codigo_producto;
	private double valor_total;
	private double valor_venta;
	private double valoriva;

	public DetalleVenta() {
	}

	public DetalleVenta(int cantidad_producto, int codigo_producto,
			double valor_total, double valor_venta,
			double valoriva) {
		this.cantidad_producto = cantidad_producto;
		this.codigo_producto = codigo_producto;
		this.valor_total = valor_total;
		this.valor_venta = valor_venta;
		this.valoriva = valoriva;
	}

	public int getCantidad_producto() {
		return cantidad_producto;
	}

	public void setCantidad_producto(int cantidad_producto) {
		this.cantidad_producto = cantidad_producto;
	}

	public int getCodigo_producto() {
		return codigo_producto;
	}

	public void setCodigo_producto(int codigo_producto) {
		this.codigo_producto = codigo_producto;
	}

	public double getValor_total() {
		return valor_total;
	}

	public void setValor_total(double valor_total) {
		this.valor_total = valor_total;
	}

	public double getValor_venta() {
		return valor_venta;
	}

	public void setValor_venta(double valor_venta) {
		this.valor_venta = valor_venta;
	}

	public double getValoriva() {
		return valoriva;
	}

	public void setValoriva(double valoriva) {
		this.valoriva = valoriva;
	}
}
