package com.mintic.clientes.servicio;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mintic.clientes.dao.IClienteRepositorio;
import com.mintic.clientes.excepcion.ResourceNotFoundException;
import com.mintic.clientes.modelo.Cliente;

@Service
@Transactional
public class ClienteServicioImpl implements ClienteServicio {
	
	@Autowired
	private IClienteRepositorio clienteRepo;
	

	@Override
	public Cliente crearCliente(Cliente cliente) {
		if (cliente.getCedula()==0 || cliente.getDireccion().equals("") || cliente.getEmail().equals("")
				|| cliente.getNombre().equals("") || cliente.getTelefono().equals("")) {
			throw new ResourceNotFoundException("Informacion incompleta");
		}
		return clienteRepo.save(cliente);
	}

	@Override
	public Cliente updateCliente(Cliente cliente) {
		if (cliente.getCedula()==0 || cliente.getDireccion().equals("") || cliente.getEmail().equals("")
				|| cliente.getNombre().equals("") || cliente.getTelefono().equals("")) {
			throw new ResourceNotFoundException("Informacion incompleta");
		}
		// Se busca el cliente con el Id del objeto cliente recibido
		Optional<Cliente> clienteDb = this.clienteRepo.findById(cliente.get_id());
		
		if(clienteDb.isPresent()) {
			// Se crea un objeto tipo Cliente con los datos recuperados
			Cliente clienteUpdate = clienteDb.get();
			// Se actualiza el valor de cada atributo con el get correspondiente de cliente
			clienteUpdate.set_id(cliente.get_id());
			clienteUpdate.setCedula(cliente.getCedula());
			clienteUpdate.setDireccion(cliente.getDireccion());;
			clienteUpdate.setEmail(cliente.getEmail());
			clienteUpdate.setNombre(cliente.getNombre());
			clienteUpdate.setTelefono(cliente.getTelefono());
			// Se actualizan los valores del objeto cliente
			clienteRepo.save(clienteUpdate);
			return clienteUpdate;
		}else {
			throw new ResourceNotFoundException("Registro no Encontrado con el Id:"+cliente.get_id());
		}
	}

	@Override
	public List<Cliente> getAllCliente() {
		// Crea la lista de los Clientes
		List<Cliente> clienteDb = this.clienteRepo.findAll();
		if(clienteDb.size() > 0) {
			return clienteDb;
		}else {
			throw new ResourceNotFoundException("No hay clientes");
		}		
	}

	@Override
	public Cliente getClienteById(String clienteId) {
		// Búsqueda de cliente por _id
		Optional<Cliente> clienteDb = this.clienteRepo.findById(clienteId);
		if(clienteDb.isPresent()) {
			return clienteDb.get();
		}else {
			throw new ResourceNotFoundException("Registro no Encontrado con el Id:"+clienteId);
		}
	}

	@Override
	public void deleteCliente(String clienteId) {
		// Busca primero
		Optional<Cliente> clienteDb = this.clienteRepo.findById(clienteId);
		if(clienteDb.isPresent()) {
			// Si lo encuentra lo borra
			this.clienteRepo.delete(clienteDb.get());
		}else {
			throw new ResourceNotFoundException("Registro no Encontrado con el Id:"+clienteId);
		}
	}

	@Override
	public Cliente getClienteByCedula(int cedula) {
		// Búsqueda de cliente por cedula
		Optional<Cliente> clienteDb = this.clienteRepo.findByCedula(cedula);
		if(clienteDb.isPresent()) {
			return clienteDb.get();
		}else {
			throw new ResourceNotFoundException("Registro no Encontrado con la cédula:"+cedula);
		}
	}

}
