package com.app.gestionPedidos.app.gestionPedidos.service;

import com.app.gestionPedidos.app.gestionPedidos.exception.ClienteNotFoundException;
import com.app.gestionPedidos.app.gestionPedidos.model.Cliente;
import com.app.gestionPedidos.app.gestionPedidos.model.Pedido;
import com.app.gestionPedidos.app.gestionPedidos.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteServiceImp implements ClienteService{


    private final ClienteRepository clienteRepository;

    @Autowired
    public ClienteServiceImp(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }


    @Override
    public Cliente guardarCliente(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    @Override
    public List<Cliente> obtenerTodosClientes() {
        return clienteRepository.findAll();
    }

    @Override
    public Cliente obtenerClientesPorId(Long id) {
        return clienteRepository.findById(id)
                .orElseThrow(() -> new ClienteNotFoundException("Cliente no encontrado con id: " + id));
    }

    @Override
    public Cliente actualizarCliente(Long id, Cliente cliente) {
        Cliente clienteEncontrado = obtenerClientesPorId(id);

            clienteEncontrado.setNombre(cliente.getNombre());
            clienteEncontrado.setEmail(cliente.getEmail());
            clienteEncontrado.setTelefono(cliente.getTelefono());
            clienteEncontrado.setDireccion(cliente.getDireccion());
            clienteEncontrado.setCiudad(cliente.getCiudad());
            clienteEncontrado.setCodigoPostal(cliente.getCodigoPostal());
            clienteEncontrado.setPais(cliente.getPais());
            clienteEncontrado.setTipoCliente(cliente.getTipoCliente());
        List<Pedido> pedidosExistentes = clienteEncontrado.getHistorialPedidos();
        List<Pedido> pedidosActualizados = cliente.getHistorialPedidos();

        pedidosExistentes.clear();
        pedidosExistentes.addAll(pedidosActualizados);

        return clienteRepository.save(clienteEncontrado);
    }

    @Override
    public void eliminarCliente(Long id) {
        Cliente clienteEncontrado = obtenerClientesPorId(id);
        clienteRepository.delete(clienteEncontrado);


    }
}
