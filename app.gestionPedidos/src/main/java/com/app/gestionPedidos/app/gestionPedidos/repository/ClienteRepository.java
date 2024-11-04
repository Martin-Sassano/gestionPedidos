package com.app.gestionPedidos.app.gestionPedidos.repository;

import com.app.gestionPedidos.app.gestionPedidos.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {


}
