package com.app.gestionPedidos.app.gestionPedidos.service;

import com.app.gestionPedidos.app.gestionPedidos.model.Cliente;

import java.util.List;

public interface ClienteService {

    Cliente guardarCliente(Cliente cliente);
    List<Cliente> obtenerTodosClientes();
    Cliente obtenerClientesPorId(Long id);
    Cliente actualizarCliente(Long id, Cliente cliente);
    void eliminarCliente(Long id);
}
