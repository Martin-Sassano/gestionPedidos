package com.app.gestionPedidos.app.gestionPedidos.service;

import com.app.gestionPedidos.app.gestionPedidos.model.Pedido;
import com.app.gestionPedidos.app.gestionPedidos.model.Producto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductoService {

    Producto guardarProductos(Producto producto);
    List<Producto> obtenerTodosProductos();
    List<Producto> obtenerProductosPorIds(List<Long> productosIds);
    Producto obtenerProductoPorId(Long id);
    Producto actualizarProducto(Long id, Producto producto);
    void eliminarProducto(Long id);
}
