package com.app.gestionPedidos.app.gestionPedidos.repository;

import com.app.gestionPedidos.app.gestionPedidos.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {
}
