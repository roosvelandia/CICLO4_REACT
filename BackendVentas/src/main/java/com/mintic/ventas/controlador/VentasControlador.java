package com.mintic.ventas.controlador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mintic.ventas.modelo.ContadorVentas;
import com.mintic.ventas.modelo.Ventas;
import com.mintic.ventas.modelo.VentasCliente;
import com.mintic.ventas.modelo.VentasSucursal;
import com.mintic.ventas.servicio.VentasServicio;


@RestController
@CrossOrigin(origins = "*", methods = { RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT,
		RequestMethod.DELETE })
@RequestMapping("/api/ventas")
public class VentasControlador {

	@Autowired
	private VentasServicio ventasServicio;
	
	@GetMapping("/ventas_cliente")
	public ResponseEntity<List<VentasCliente>> getAllVentasxCliente(){
		return ResponseEntity.ok().body(ventasServicio.ventasCliente());
	}
	
	@GetMapping("/ventas_contador")
	public ResponseEntity<List<ContadorVentas>> getContadorVentas(){
		return ResponseEntity.ok().body(ventasServicio.countventas());
	}
	
	@GetMapping("/ventas_sucursal")
	public ResponseEntity<List<VentasSucursal>> getAllVentasxSucursal(){
		return ResponseEntity.ok().body(ventasServicio.ventasSucursal());
	}
	
	@GetMapping("/ventas")
	public ResponseEntity<List<Ventas>> getAllVentas(){
		return ResponseEntity.ok().body(ventasServicio.getAllVentas());
	}
	
	@GetMapping("/ventas/{id}")
	public ResponseEntity<Ventas> getVentasById(@PathVariable String id) {
		return ResponseEntity.ok().body(ventasServicio.getVentasById(id));
	}
	
	@PostMapping("/ventas")
	public ResponseEntity < Ventas > crearVenta(@RequestBody Ventas ventas){
		return ResponseEntity.ok().body(this.ventasServicio.crearVentas(ventas));
	}
	
	@PutMapping("/ventas/{id}")
	public ResponseEntity<Ventas> updateVenta(@PathVariable String id, @RequestBody Ventas ventas){
		ventas.set_id(id);
		return ResponseEntity.ok().body(this.ventasServicio.updateVentas(ventas));
	}
	
	@DeleteMapping("/ventas/{id}")
	public HttpStatus deleteVenta(@PathVariable String id) {
		this.ventasServicio.deleteVentas(id);
		return HttpStatus.OK;
	}
	
}
