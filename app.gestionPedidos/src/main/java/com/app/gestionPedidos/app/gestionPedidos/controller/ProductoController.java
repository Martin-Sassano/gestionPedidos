package com.app.gestionPedidos.app.gestionPedidos.controller;

import com.app.gestionPedidos.app.gestionPedidos.dto.DTOMapper;
import com.app.gestionPedidos.app.gestionPedidos.dto.PedidoDTO;
import com.app.gestionPedidos.app.gestionPedidos.dto.ProductoDTO;
import com.app.gestionPedidos.app.gestionPedidos.model.Producto;
import com.app.gestionPedidos.app.gestionPedidos.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {

    private final ProductoService productoService;

    @Autowired
    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }

    @GetMapping
    public ResponseEntity<List<ProductoDTO>> obtenerTodosProductos(){

        List<Producto> producto = productoService.obtenerTodosProductos();
        List<ProductoDTO> productoDTO = producto.stream().map(productos -> DTOMapper.toProductoDTO(productos)).collect(Collectors.toList());

        if (producto.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(productoDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductoDTO> obtenerProductoPorId(@PathVariable Long id){

        Producto producto = productoService.obtenerProductoPorId(id);
        ProductoDTO productoDTO = DTOMapper.toProductoDTO(producto);

        return ResponseEntity.ok(productoDTO);
    }

    @PostMapping
    public ResponseEntity<ProductoDTO> guardarProductos(@RequestBody ProductoDTO productoDTO){

        Producto producto = DTOMapper.toProductoEntity(productoDTO);
        Producto productoGuardado = productoService.guardarProductos(producto);
        ProductoDTO productoDTO1 = DTOMapper.toProductoDTO(productoGuardado);

        return ResponseEntity.status(HttpStatus.CREATED).body(productoDTO1);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductoDTO> actualizarProducto(@PathVariable Long id, @RequestBody ProductoDTO productoDTO){

        Producto producto = DTOMapper.toProductoEntity(productoDTO);
        Producto productoEditado = productoService.actualizarProducto(id, producto);
        ProductoDTO productoDTO1 = DTOMapper.toProductoDTO(productoEditado);

        return ResponseEntity.ok(productoDTO1);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarProducto(@PathVariable Long id){
        productoService.eliminarProducto(id);
        return ResponseEntity.noContent().build();
    }
}
