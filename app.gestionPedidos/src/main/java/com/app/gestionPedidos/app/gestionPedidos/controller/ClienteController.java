package com.app.gestionPedidos.app.gestionPedidos.controller;

import com.app.gestionPedidos.app.gestionPedidos.dto.ClienteDTO;
import com.app.gestionPedidos.app.gestionPedidos.dto.DTOMapper;
import com.app.gestionPedidos.app.gestionPedidos.model.Cliente;
import com.app.gestionPedidos.app.gestionPedidos.service.ClienteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    private final ClienteService clienteService;

    @Autowired
    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping
    public ResponseEntity<List<ClienteDTO>> obtenerTodosClientes() {
        List<Cliente> clientes = clienteService.obtenerTodosClientes();

        List<ClienteDTO> clienteDTOs = clientes.stream()
                .map(cliente -> DTOMapper.toClienteDTO(cliente))
                .collect(Collectors.toList());

        if (clienteDTOs.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(clienteDTOs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteDTO> obtenerClientesPorId(@PathVariable Long id){
        Cliente cliente = clienteService.obtenerClientesPorId(id);
        ClienteDTO clienteDTO = DTOMapper.toClienteDTO(cliente);
        return ResponseEntity.ok(clienteDTO);
    }

    @PostMapping
    public ResponseEntity<ClienteDTO> guardarCliente(@Valid @RequestBody ClienteDTO clienteDTO){
        Cliente cliente = DTOMapper.toClienteEntity(clienteDTO);
        Cliente clienteGuardado = clienteService.guardarCliente(cliente);
        ClienteDTO clienteGuardadoDTO = DTOMapper.toClienteDTO(clienteGuardado);
        return ResponseEntity.status(HttpStatus.CREATED).body(clienteGuardadoDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClienteDTO> actualizarCliente(@PathVariable Long id, @Valid @RequestBody ClienteDTO clienteDTO){
        Cliente cliente = DTOMapper.toClienteEntity(clienteDTO);
        Cliente clienteAct = clienteService.actualizarCliente(id, cliente);
        ClienteDTO clienteDTOAct = DTOMapper.toClienteDTO(clienteAct);
        return ResponseEntity.ok(clienteDTOAct);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCliente(@PathVariable Long id){
        clienteService.eliminarCliente(id);
        return ResponseEntity.noContent().build();
    }
}
