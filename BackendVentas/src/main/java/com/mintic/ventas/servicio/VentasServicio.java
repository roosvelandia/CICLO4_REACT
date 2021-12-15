package com.mintic.ventas.servicio;

import java.util.List;

import com.mintic.ventas.modelo.ContadorVentas;
import com.mintic.ventas.modelo.Ventas;
import com.mintic.ventas.modelo.VentasCliente;
import com.mintic.ventas.modelo.VentasSucursal;

public interface VentasServicio {
	
	// Cabeceras de métodos a utilizar en la colección Ventas
	// Crear
    Ventas crearVentas(Ventas ventas);

    //Actualizar
    Ventas updateVentas(Ventas ventas);

    //Listado de Ventas
    List<Ventas> getAllVentas();

    //Buscar Ventas por _id
    Ventas getVentasById(String ventasId);

    //Eliminar un Ventas
    void deleteVentas(String ventasId);
    
    List<VentasCliente> ventasCliente();
    
    List<VentasSucursal> ventasSucursal();
    
    List<ContadorVentas> countventas();
	

}
