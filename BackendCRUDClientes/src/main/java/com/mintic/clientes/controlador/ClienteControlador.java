package com.mintic.clientes.controlador;

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

import com.mintic.clientes.modelo.Cliente;
import com.mintic.clientes.servicio.ClienteServicio;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
//@CrossOrigin(origins = "*", methods = { RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT,
//		RequestMethod.DELETE })
@RequestMapping("/api/clientes")
public class ClienteControlador {

	@Autowired
	private ClienteServicio clienteServicio;
	
	@CrossOrigin(origins = { "http://localhost:3000/" })
	@GetMapping("/clientes")
	public ResponseEntity<List<Cliente>> getAllClientes() {
		return ResponseEntity.ok().body(clienteServicio.getAllCliente());
	}

	@GetMapping("/clientes/{id}")
	public ResponseEntity<Cliente> getClienteById(@PathVariable String id) {
		try {
			return ResponseEntity.ok().body(clienteServicio.getClienteById(id));
		} catch (Exception ex) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/clientes")
	public ResponseEntity<Cliente> crearCliente(@RequestBody Cliente cliente) {
		try {
			return ResponseEntity.ok().body(this.clienteServicio.crearCliente(cliente));
		} catch (Exception ex) {
			return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
		}
	}

	@PutMapping("/clientes/{id}")
	public ResponseEntity<Cliente> updateCliente(@PathVariable String id, @RequestBody Cliente cliente) {
		try {
			cliente.set_id(id);
			return ResponseEntity.ok().body(this.clienteServicio.updateCliente(cliente));
		} catch (Exception ex) {
			if (ex.getMessage().equals("Informacion incompleta")) {
				return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		}
	}

	@GetMapping("/clientes/cedula/{cedula}")
	public ResponseEntity<Cliente> getClienteByCedula(@PathVariable int cedula) {
		try {
			System.out.println(cedula);
			return ResponseEntity.ok().body(this.clienteServicio.getClienteByCedula(cedula));
		} catch (Exception ex) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/clientes/{id}")
	public HttpStatus deleteCliente(@PathVariable String id) {
		try {
			this.clienteServicio.deleteCliente(id);
			return HttpStatus.OK;
		} catch (Exception ex) {
			return HttpStatus.NOT_FOUND;
		}
	}

}
