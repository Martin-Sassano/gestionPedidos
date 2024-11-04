package com.app.gestionPedidos.app.gestionPedidos.data;

import com.app.gestionPedidos.app.gestionPedidos.model.Cliente;
import com.app.gestionPedidos.app.gestionPedidos.model.Pedido;
import com.app.gestionPedidos.app.gestionPedidos.model.Producto;
import com.app.gestionPedidos.app.gestionPedidos.repository.ClienteRepository;
import com.app.gestionPedidos.app.gestionPedidos.repository.PedidoRepository;
import com.app.gestionPedidos.app.gestionPedidos.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Arrays;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private PedidoRepository pedidoRepository;

    @Override
    public void run(String... args) throws Exception {
        // Verifica si la base de datos está vacía
        if (clienteRepository.count() == 0) {
            cargarClientes();
        }

        if (productoRepository.count() == 0) {
            cargarProductos();
        }

        if (pedidoRepository.count() == 0) {
            cargarPedidos();
        }
    }

    private void cargarClientes() {
        Cliente cliente1 = new Cliente("Juan Pérez", "juan.perez@example.com");
        Cliente cliente2 = new Cliente("María Gómez", "maria.gomez@example.com");
        clienteRepository.saveAll(Arrays.asList(cliente1, cliente2));
        System.out.println("Clientes cargados.");
    }

    private void cargarProductos() {
        Producto producto1 = new Producto("Laptop", "Laptop gaming", BigDecimal.valueOf(1200.00));
        Producto producto2 = new Producto("Teléfono", "Teléfono inteligente", BigDecimal.valueOf(800.00));
        Producto producto3 = new Producto("Auriculares", "Auriculares inalámbricos", BigDecimal.valueOf(150.00));
        productoRepository.saveAll(Arrays.asList(producto1, producto2, producto3));
        System.out.println("Productos cargados.");
    }

    private void cargarPedidos() {
        // Supongamos que ya tienes clientes y productos en la base de datos
        Cliente cliente = clienteRepository.findById(1L).orElseThrow();
        Producto producto1 = productoRepository.findById(1L).orElseThrow();
        Producto producto2 = productoRepository.findById(2L).orElseThrow();

        Pedido pedido1 = new Pedido(cliente, Arrays.asList(producto1, producto2), BigDecimal.valueOf(1950.00), "Pendiente");
        pedidoRepository.save(pedido1);
        System.out.println("Pedidos cargados.");
    }
}
