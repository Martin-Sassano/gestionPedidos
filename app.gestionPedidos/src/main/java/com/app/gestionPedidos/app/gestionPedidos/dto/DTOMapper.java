package com.app.gestionPedidos.app.gestionPedidos.dto;

import com.app.gestionPedidos.app.gestionPedidos.model.Cliente;
import com.app.gestionPedidos.app.gestionPedidos.model.Pedido;
import com.app.gestionPedidos.app.gestionPedidos.model.Producto;

import java.util.List;
import java.util.stream.Collectors;

public class DTOMapper {

    public static ClienteDTO toClienteDTO(Cliente cliente) {
        ClienteDTO clienteDTO = new ClienteDTO();
        clienteDTO.setId(cliente.getId());
        clienteDTO.setNombre(cliente.getNombre());
        clienteDTO.setEmail(cliente.getEmail());
        clienteDTO.setTelefono(cliente.getTelefono());
        clienteDTO.setDireccion(cliente.getDireccion());
        clienteDTO.setPedidosIds(cliente.getHistorialPedidos().stream().map(Pedido::getId).collect(Collectors.toList()));
        return clienteDTO;
    }

    public static PedidoDTO toPedidoDTO(Pedido pedido) {
        PedidoDTO pedidoDTO = new PedidoDTO();
        pedidoDTO.setId(pedido.getId());
        pedidoDTO.setFechaCreacion(pedido.getFechaCreacion());
        pedidoDTO.setEstado(pedido.getEstado());
        pedidoDTO.setTotal(pedido.getTotal());
        pedidoDTO.setClienteId(pedido.getCliente().getId());
        pedidoDTO.setProductosIds(pedido.getListaDeProductos().stream().map(Producto::getId).collect(Collectors.toList()));
        return pedidoDTO;
    }

    public static ProductoDTO toProductoDTO(Producto producto) {
        ProductoDTO productoDTO = new ProductoDTO();
        productoDTO.setId(producto.getId());
        productoDTO.setNombre(producto.getNombre());
        productoDTO.setDescripcion(producto.getDescripcion());
        productoDTO.setPrecio(producto.getPrecio());
        productoDTO.setCantidadStock(producto.getCantidadStock());
        return productoDTO;
    }

    // Métodos inversos (para convertir DTOs a Entidades)

    public static Cliente toClienteEntity(ClienteDTO clienteDTO) {
        Cliente cliente = new Cliente();
        cliente.setId(clienteDTO.getId());
        cliente.setNombre(clienteDTO.getNombre());
        cliente.setEmail(clienteDTO.getEmail());
        cliente.setTelefono(clienteDTO.getTelefono());
        cliente.setDireccion(clienteDTO.getDireccion());
        return cliente;
    }

    public static Pedido toPedidoEntity(PedidoDTO pedidoDTO, Cliente cliente, List<Producto> productos) {
        Pedido pedido = new Pedido();
        pedido.setId(pedidoDTO.getId());
        pedido.setFechaCreacion(pedidoDTO.getFechaCreacion());
        pedido.setEstado(pedidoDTO.getEstado());
        pedido.setTotal(pedidoDTO.getTotal());
        pedido.setCliente(cliente);
        pedido.setListaDeProductos(productos);
        return pedido;
    }

    public static Producto toProductoEntity(ProductoDTO productoDTO) {
        Producto producto = new Producto();
        producto.setId(productoDTO.getId());
        producto.setNombre(productoDTO.getNombre());
        producto.setDescripcion(productoDTO.getDescripcion());
        producto.setPrecio(productoDTO.getPrecio());
        producto.setCantidadStock(productoDTO.getCantidadStock());
        return producto;
    }
}
