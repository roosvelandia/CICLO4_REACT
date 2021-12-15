package com.mintic.productos.servicio;

import java.util.List;

import com.mintic.productos.modelo.Productos;


public interface ProductoServicio {
	
    Productos crearProducto(Productos producto);

    Productos updateProducto(Productos producto);

    List<Productos> getAllProducto();

    Productos getProductoById(String productoId);

    Productos getProductoByCodigo(int codigo);

    void deleteProducto(String productoId);	

}
