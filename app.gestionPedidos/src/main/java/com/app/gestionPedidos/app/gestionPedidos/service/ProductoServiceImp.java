package com.app.gestionPedidos.app.gestionPedidos.service;

import com.app.gestionPedidos.app.gestionPedidos.model.Producto;
import com.app.gestionPedidos.app.gestionPedidos.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoServiceImp implements ProductoService {

    private final ProductoRepository productoRepository;


    @Autowired
    public ProductoServiceImp(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    @Override
    public Producto guardarProductos(Producto producto) {
        return productoRepository.save(producto);
    }

    @Override
    public List<Producto> obtenerTodosProductos() {
        return productoRepository.findAll();
    }

    @Override
    public List<Producto> obtenerProductosPorIds(List<Long> productosIds) {
        return productoRepository.findAllById(productosIds);
    }

    @Override
    public Producto obtenerProductoPorId(Long id) {
        return productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado con id: " + id));
    }

    @Override
    public Producto actualizarProducto(Long id, Producto producto) {
        Producto productoEncontrado = obtenerProductoPorId(id);
        productoEncontrado.setCategoria(producto.getCategoria());
        productoEncontrado.setDescripcion(producto.getDescripcion());
        productoEncontrado.setNombre(producto.getNombre());
        productoEncontrado.setPrecio(producto.getPrecio());
        productoEncontrado.setCantidadStock(producto.getCantidadStock());
        return productoRepository.save(productoEncontrado);
    }

    @Override
    public void eliminarProducto(Long id) {
        Producto productoEncontrado = obtenerProductoPorId(id);
        productoRepository.delete(productoEncontrado);
    }
}
