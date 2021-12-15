package com.mintic.ventas.dao;

import java.util.List;

import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.mintic.ventas.modelo.ContadorVentas;
import com.mintic.ventas.modelo.Ventas;
import com.mintic.ventas.modelo.VentasCliente;
import com.mintic.ventas.modelo.VentasSucursal;

@Repository
public interface IVentasRepositorio extends MongoRepository<Ventas, String> {
	
	@Aggregation("{$group: {_id: {cedula: '$cedula_cliente'},nombre: {$last: '$nombre_cliente'}, total: {$sum: '$total_venta'}}}")
	List<VentasCliente> sumVentasCliente();
	
	@Aggregation("{$group: {_id: {ciudad: '$sucursal'},total: {$sum: '$total_venta'}}}")
	List<VentasSucursal> sumVentasSucursal();
	
	@Aggregation("{$group: {_id: null,contador: {$sum: 1}}}")
	List<ContadorVentas> countVentas();
}
