package com.mintic.clientes.dao;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.mintic.clientes.modelo.Cliente;

@Repository
public interface IClienteRepositorio extends MongoRepository<Cliente, String> {

	Optional<Cliente> findByCedula(int cedula);
}
