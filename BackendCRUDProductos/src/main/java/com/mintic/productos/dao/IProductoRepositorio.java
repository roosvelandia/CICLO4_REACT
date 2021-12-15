package com.mintic.productos.dao;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.mintic.productos.modelo.Productos;


@Repository
public interface IProductoRepositorio extends MongoRepository<Productos, String> {

	Optional<Productos> findByCodigo(int codigo);
}
