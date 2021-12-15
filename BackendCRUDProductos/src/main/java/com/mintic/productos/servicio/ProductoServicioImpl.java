package com.mintic.productos.servicio;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mintic.productos.dao.IProductoRepositorio;
import com.mintic.productos.excepcion.ResourceNotFoundException;
import com.mintic.productos.modelo.Productos;

@Service
@Transactional
public class ProductoServicioImpl implements ProductoServicio {
	
	@Autowired
	private IProductoRepositorio productoRepo;
	

	@Override
	public Productos crearProducto(Productos producto) {
		if (producto.getCodigo()==0 || producto.getNombre().equals("") || producto.getNitproveedor()==0
				|| producto.getPrecioCompra()==0 || producto.getPrecioVenta()==0) {
			throw new ResourceNotFoundException("Informacion incompleta");
		}
		return productoRepo.save(producto);
	}

	@Override
	public Productos updateProducto(Productos producto) {
		if (producto.getCodigo()==0 || producto.getNombre().equals("") || producto.getNitproveedor()==0
				|| producto.getPrecioCompra()==0 || producto.getPrecioVenta()==0) {
			throw new ResourceNotFoundException("Informacion incompleta");
		}
		// Se busca el producto con el Id del objeto producto recibido
		Optional<Productos> productoDb = this.productoRepo.findById(producto.get_id());
		
		if(productoDb.isPresent()) {
			// Se crea un objeto tipo Producto con los datos recuperados
			Productos productoUpdate = productoDb.get();
			// Se actualiza el valor de cada atributo con el get correspondiente de producto
			productoUpdate.set_id(producto.get_id());
			productoUpdate.setCodigo(producto.getCodigo());
			productoUpdate.setNombre(producto.getNombre());;
			productoUpdate.setPrecioCompra(producto.getPrecioCompra());
			productoUpdate.setIvacompra(producto.getIvacompra());
			productoUpdate.setPrecioVenta(producto.getPrecioVenta());
			// Se actualizan los valores del objeto producto
			productoRepo.save(productoUpdate);
			return productoUpdate;
		}else {
			throw new ResourceNotFoundException("Registro no Encontrado con el Id:"+producto.get_id());
		}
	}

	@Override
	public List<Productos> getAllProducto() {
		// Crea la lista de los Productos
		List<Productos> productoDb = this.productoRepo.findAll();
		if(productoDb.size() > 0) {
			return productoDb;
		}else {
			throw new ResourceNotFoundException("No hay productos");
		}		
	}

	@Override
	public Productos getProductoById(String productoId) {
		// Búsqueda de producto por _id
		Optional<Productos> productoDb = this.productoRepo.findById(productoId);
		if(productoDb.isPresent()) {
			return productoDb.get();
		}else {
			throw new ResourceNotFoundException("Registro no Encontrado con el Id:"+productoId);
		}
	}

	@Override
	public void deleteProducto(String productoId) {
		// Busca primero
		Optional<Productos> productoDb = this.productoRepo.findById(productoId);
		if(productoDb.isPresent()) {
			// Si lo encuentra lo borra
			this.productoRepo.delete(productoDb.get());
		}else {
			throw new ResourceNotFoundException("Registro no Encontrado con el Id:"+productoId);
		}
	}

	@Override
	public Productos getProductoByCodigo(int codigo) {
		// Búsqueda de producto por codigo
		Optional<Productos> productoDb = this.productoRepo.findByCodigo(codigo);
		if(productoDb.isPresent()) {
			return productoDb.get();
		}else {
			throw new ResourceNotFoundException("Registro no Encontrado con el codigo:"+codigo);
		}
	}

}
