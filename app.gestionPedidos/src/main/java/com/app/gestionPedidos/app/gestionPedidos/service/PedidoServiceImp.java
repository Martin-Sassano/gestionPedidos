package com.app.gestionPedidos.app.gestionPedidos.service;

import com.app.gestionPedidos.app.gestionPedidos.exception.ClienteNotFoundException;
import com.app.gestionPedidos.app.gestionPedidos.exception.PedidoNotFoundException;
import com.app.gestionPedidos.app.gestionPedidos.model.Cliente;
import com.app.gestionPedidos.app.gestionPedidos.model.Pedido;
import com.app.gestionPedidos.app.gestionPedidos.model.Producto;
import com.app.gestionPedidos.app.gestionPedidos.repository.ClienteRepository;
import com.app.gestionPedidos.app.gestionPedidos.repository.PedidoRepository;
import com.app.gestionPedidos.app.gestionPedidos.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PedidoServiceImp implements PedidoService{

    private final PedidoRepository pedidoRepository;
    private final ProductoRepository productoRepository;
    private final ClienteRepository clienteRepository;

    @Autowired
    public PedidoServiceImp(PedidoRepository pedidoRepository, ProductoRepository productoRepository, ClienteRepository clienteRepository) {
        this.pedidoRepository = pedidoRepository;
        this.productoRepository = productoRepository;
        this.clienteRepository = clienteRepository;
    }


    @Override
    public Pedido guardarPedido(Pedido pedido) {
        Cliente cliente = clienteRepository.findById(pedido.getCliente().getId())
                .orElseThrow(() -> new ClienteNotFoundException("Cliente no encontrado"));

        // Agregar el pedido al historial del cliente
        cliente.getHistorialPedidos().add(pedido);

        // Guardar el pedido y el cliente
        pedido.setCliente(cliente);

        return pedidoRepository.save(pedido);
    }

    @Override
    public List<Pedido> obtenerTodosPedidos() {
        return pedidoRepository.findAll();
    }

    @Override
    public Pedido obtenerPedidosPorId(Long id) {
        return pedidoRepository.findById(id).orElseThrow(() -> new PedidoNotFoundException("Cliente con el id "+ id + " no encontrado"));
    }

    @Override
    public Pedido actualizarPedido(Long id, Pedido pedido) {
        Pedido pedidoEncontrado = obtenerPedidosPorId(id);

        // Actualizar campos básicos
        pedidoEncontrado.setEstado(pedido.getEstado());
        pedidoEncontrado.setFechaCreacion(pedido.getFechaCreacion());
        pedidoEncontrado.setFechaDeEnvio(pedido.getFechaDeEnvio());
        pedidoEncontrado.setMetodoPago(pedido.getMetodoPago());

        // 1. Actualizar la lista de productos
        List<Producto> productosActualizados = productoRepository.findAllById(
                pedido.getListaDeProductos().stream()
                        .map(Producto::getId)
                        .collect(Collectors.toList())
        );
        pedidoEncontrado.setListaDeProductos(productosActualizados);

        // 2. Actualizar el cliente
        Cliente clienteActualizado = clienteRepository.findById(pedido.getId())
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado con id: " + pedido.getId()));
        pedidoEncontrado.setId(clienteActualizado.getId());

        // Guardar y retornar el pedido actualizado
        return pedidoRepository.save(pedidoEncontrado);
    }

    @Override
    public void eliminarPedido(Long id) {
        Pedido pedido = obtenerPedidosPorId(id);

        pedidoRepository.delete(pedido);
    }
}
