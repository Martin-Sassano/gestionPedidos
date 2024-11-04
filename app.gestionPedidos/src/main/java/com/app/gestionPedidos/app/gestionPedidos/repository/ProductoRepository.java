package com.app.gestionPedidos.app.gestionPedidos.repository;

import com.app.gestionPedidos.app.gestionPedidos.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {
}
