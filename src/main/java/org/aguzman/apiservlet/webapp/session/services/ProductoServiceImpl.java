package org.aguzman.apiservlet.webapp.session.services;



import org.aguzman.apiservlet.webapp.session.model.Categoria;
import org.aguzman.apiservlet.webapp.session.model.Producto;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class ProductoServiceImpl implements ProductoService{
    @Override
    public List<Producto> listar() {
        return Arrays.asList(new Producto(1L, "notebook", "computacion", 175000),
                new Producto(2L, "mesa escritorio", "oficina", 100000),
                new Producto(3L, "teclado mecanico", "computacion", 40000));
    }

    @Override
    public Optional<Producto> findById(Long id) {
        return listar().stream().filter( p -> p.getId().equals(id)).findAny();
    }

    @Override
    public void save(Producto producto) {

    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public List<Categoria> listarCategorias() {
        return null;
    }

    @Override
    public Optional<Categoria> findByIdCategoria(Long id) {
        return Optional.empty();
    }
}