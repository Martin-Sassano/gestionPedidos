package com.app.gestionPedidos.app.gestionPedidos.controller;

import com.app.gestionPedidos.app.gestionPedidos.dto.ClienteDTO;
import com.app.gestionPedidos.app.gestionPedidos.dto.DTOMapper;
import com.app.gestionPedidos.app.gestionPedidos.dto.PedidoDTO;
import com.app.gestionPedidos.app.gestionPedidos.model.Cliente;
import com.app.gestionPedidos.app.gestionPedidos.model.Pedido;
import com.app.gestionPedidos.app.gestionPedidos.model.Producto;
import com.app.gestionPedidos.app.gestionPedidos.service.ClienteService;
import com.app.gestionPedidos.app.gestionPedidos.service.PedidoService;
import com.app.gestionPedidos.app.gestionPedidos.service.ProductoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {

    private final PedidoService pedidoService;
    private final ClienteService clienteService;
    private final ProductoService productoService;

    @Autowired
    public PedidoController(PedidoService pedidoService, ClienteService clienteService, ProductoService productoService) {
        this.pedidoService = pedidoService;
        this.clienteService = clienteService;
        this.productoService = productoService;
    }

    @GetMapping
    public ResponseEntity<List<PedidoDTO>> obtenerTodosPedidos() {
        List<Pedido> pedidos = pedidoService.obtenerTodosPedidos();
        List<PedidoDTO> pedidoDTO = pedidos.stream().map(pedido -> DTOMapper.toPedidoDTO(pedido)).collect(Collectors.toList());

        if (pedidos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(pedidoDTO);
    }


    @GetMapping("/{id}")
    public ResponseEntity<PedidoDTO> obtenerPedidosPorId(@PathVariable Long id){
        Pedido pedido = pedidoService.obtenerPedidosPorId(id);
        PedidoDTO pedidoDTO = DTOMapper.toPedidoDTO(pedido);
        return ResponseEntity.ok(pedidoDTO);
    }

    @PostMapping
    public ResponseEntity<PedidoDTO> guardarPedido(@Valid @RequestBody PedidoDTO pedidoDTO) {

        // Buscar el cliente por ID
        Cliente cliente = clienteService.obtenerClientesPorId(pedidoDTO.getClienteId());

        // Obtener la lista de productos por sus IDs
        List<Producto> productos = productoService.obtenerProductosPorIds(pedidoDTO.getProductosIds());

        // Convertir el DTO a entidad usando los parámetros adicionales
        Pedido pedido = DTOMapper.toPedidoEntity(pedidoDTO, cliente, productos);

        // Guardar el pedido
        Pedido pedidoGuardado = pedidoService.guardarPedido(pedido);

        // Convertir la entidad guardada nuevamente a DTO
        PedidoDTO pedidoGuardadoDTO = DTOMapper.toPedidoDTO(pedidoGuardado);

        // Retornar el DTO con el código 201 Created
        return ResponseEntity.status(HttpStatus.CREATED).body(pedidoGuardadoDTO);
    }

    //////////////////////////////
    @PutMapping("/{id}")
    public ResponseEntity<PedidoDTO> actualizarPedido(@PathVariable Long id, @Valid @RequestBody PedidoDTO pedidoDTO){
        // Obtener el cliente por ID
        Cliente cliente = clienteService.obtenerClientesPorId(pedidoDTO.getClienteId());

        // Obtener la lista de productos por sus IDs
        List<Producto> productos = productoService.obtenerProductosPorIds(pedidoDTO.getProductosIds());

        // Convertir el DTO a entidad usando los parámetros adicionales
        Pedido pedido = DTOMapper.toPedidoEntity(pedidoDTO, cliente, productos);

        // Llamar al servicio para actualizar el pedido
        Pedido pedidoActualizado = pedidoService.actualizarPedido(id, pedido);

        // Convertir el pedido actualizado a DTO para la respuesta
        PedidoDTO pedidoDTO1 = DTOMapper.toPedidoDTO(pedidoActualizado);

        return ResponseEntity.ok().body(pedidoDTO1);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarPedido(@PathVariable Long id){

        pedidoService.eliminarPedido(id);
        return ResponseEntity.noContent().build();
    }
}
