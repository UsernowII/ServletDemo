package org.aguzman.apiservlet.webapp.session.services;

import org.aguzman.apiservlet.webapp.session.model.Producto;


import java.util.List;
import java.util.Optional;

public interface ProductoService {


    List<Producto> listar();

    Optional<Producto> findById(Long id);

}