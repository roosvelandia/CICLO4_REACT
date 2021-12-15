package com.mintic.productos.controlador;

import java.io.IOException;
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
import org.springframework.web.bind.annotation.RestController;


import com.mintic.productos.modelo.Files;
import com.mintic.productos.modelo.LectorCSV;
import com.mintic.productos.modelo.Productos;
import com.mintic.productos.servicio.ProductoServicio;


@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
//@CrossOrigin(origins = "*", methods = { RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT,
//		RequestMethod.DELETE })
@RequestMapping("/api/productos")
public class ProductoControlador {

	@Autowired
	private ProductoServicio productoServicio;
	
	@CrossOrigin(origins = { "http://localhost:3000/" })
	@PostMapping("/productos/cargue") // ruta del servicio desde el front deben direccionar a esta ruta
	public int  cargarProductos(@RequestBody Files file) throws IOException ,NumberFormatException{
		LectorCSV lectorCSV = new LectorCSV(',',' ');
		
		try {
			String archivo=file.getFilepath();
			if (!archivo.contains(".csv") && !archivo.equals("")) {
				return 3;
			}
			System.out.println("Nombre archivo: " + archivo);
			List<Productos> productos = lectorCSV.LeerCSVSimple(archivo);		
			for (Productos producto :productos) {
				System.out.println(producto);
				productoServicio.crearProducto(producto);
			}
			return 1; //cargue exitoso
		
		} catch (IOException ioe){
        	System.out.println("catch lectura archivo: "+ ioe.toString());
        	return 2; //No se selecciona el archivo
        	
        }  catch (Exception eio) {
        	System.out.println("error por formato archivo: "+ eio.toString());
			return 0;//ha ocurrido otro error
			
		}
	}

	@GetMapping("/productos")
	public ResponseEntity<List<Productos>> getAllProductos() {
		return ResponseEntity.ok().body(productoServicio.getAllProducto());
	}

	@GetMapping("/productos/{id}")
	public ResponseEntity<Productos> getProductoById(@PathVariable String id) {
		try {
			return ResponseEntity.ok().body(productoServicio.getProductoById(id));
		} catch (Exception ex) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/productos")
	public ResponseEntity<Productos> crearProducto(@RequestBody Productos producto) {
		try {
			return ResponseEntity.ok().body(this.productoServicio.crearProducto(producto));
		} catch (Exception ex) {
			return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
		}
	}

	@PutMapping("/productos/{id}")
	public ResponseEntity<Productos> updateProducto(@PathVariable String id, @RequestBody Productos producto) {
		try {
			producto.set_id(id);
			return ResponseEntity.ok().body(this.productoServicio.updateProducto(producto));
		} catch (Exception ex) {
			if (ex.getMessage().equals("Informacion incompleta")) {
				return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		}
	}

	@GetMapping("/productos/codigo/{codigo}")
	public ResponseEntity<Productos> getProductoByCodigo(@PathVariable int codigo) {
		try {
			
			return ResponseEntity.ok().body(this.productoServicio.getProductoByCodigo(codigo));
		} catch (Exception ex) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/productos/{id}")
	public HttpStatus deleteProducto(@PathVariable String id) {
		try {
			this.productoServicio.deleteProducto(id);
			return HttpStatus.OK;
		} catch (Exception ex) {
			return HttpStatus.NOT_FOUND;
		}
	}

}
