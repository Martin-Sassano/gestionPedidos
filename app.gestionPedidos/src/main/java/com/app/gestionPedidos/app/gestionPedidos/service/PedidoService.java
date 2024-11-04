package com.app.gestionPedidos.app.gestionPedidos.service;

import com.app.gestionPedidos.app.gestionPedidos.model.Cliente;
import com.app.gestionPedidos.app.gestionPedidos.model.Pedido;

import java.util.List;

public interface PedidoService {

    Pedido guardarPedido(Pedido pedido);
    List<Pedido> obtenerTodosPedidos();
    Pedido obtenerPedidosPorId(Long id);
    Pedido actualizarPedido(Long id, Pedido pedido);
    void eliminarPedido(Long id);
}
