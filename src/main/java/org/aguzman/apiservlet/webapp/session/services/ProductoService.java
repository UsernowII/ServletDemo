package org.aguzman.apiservlet.webapp.session.services;

import org.aguzman.apiservlet.webapp.session.model.Categoria;
import org.aguzman.apiservlet.webapp.session.model.Producto;


import java.util.List;
import java.util.Optional;

public interface ProductoService {


    List<Producto> listar();

    Optional<Producto> findById(Long id);

    void save(Producto producto);

    void delete(Long id);

    List<Categoria> listarCategorias();

    Optional<Categoria> findByIdCategoria(Long id);

}