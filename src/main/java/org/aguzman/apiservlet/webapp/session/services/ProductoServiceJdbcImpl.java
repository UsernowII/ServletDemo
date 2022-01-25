package org.aguzman.apiservlet.webapp.session.services;

import org.aguzman.apiservlet.webapp.session.model.Categoria;
import org.aguzman.apiservlet.webapp.session.model.Producto;
import org.aguzman.apiservlet.webapp.session.repositories.CategoriaRepositoryImpl;
import org.aguzman.apiservlet.webapp.session.repositories.ProductoRepositoryImpl;
import org.aguzman.apiservlet.webapp.session.repositories.Repository;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class ProductoServiceJdbcImpl implements ProductoService{

    private Repository<Producto> productoRepository;
    private Repository<Categoria> categoriaRepository;


    public ProductoServiceJdbcImpl(Connection connection) {
        this.productoRepository = new ProductoRepositoryImpl(connection);
        this.categoriaRepository = new CategoriaRepositoryImpl(connection);
    }

    @Override
    public List<Producto> listar() {
        try {
            return productoRepository.listar();
        } catch (SQLException e) {
            throw new ServiceJdbcException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public Optional<Producto> findById(Long id) {
        try {
            return Optional.ofNullable(productoRepository.findById(id));
        } catch (SQLException e) {
            throw new ServiceJdbcException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public void save(Producto producto) {
        try {
            productoRepository.save(producto);
        } catch (SQLException e) {
            throw new ServiceJdbcException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public void delete(Long id) {
        try {
            productoRepository.delete(id);
        } catch (SQLException e) {
            throw new ServiceJdbcException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public List<Categoria> listarCategorias() {
        try {
            return categoriaRepository.listar();
        } catch (SQLException e) {
            throw new ServiceJdbcException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public Optional<Categoria> findByIdCategoria(Long id) {
        try {
            return Optional.ofNullable(categoriaRepository.findById(id));
        } catch (SQLException e) {
            throw new ServiceJdbcException(e.getMessage(), e.getCause());
        }
    }


}
