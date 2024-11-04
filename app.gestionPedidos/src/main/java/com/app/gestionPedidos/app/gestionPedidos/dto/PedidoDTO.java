package com.app.gestionPedidos.app.gestionPedidos.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class PedidoDTO {

    private Long id;

    @NotNull(message = "La fecha de creación es obligatoria")
    private LocalDateTime fechaCreacion;

    @NotNull(message = "El estado es obligatorio")
    @Size(min = 3, max = 20, message = "El estado debe tener entre 3 y 20 caracteres")
    private String estado;

    @Positive(message = "El total debe ser un valor positivo")
    private BigDecimal total;

    @NotNull(message = "El ID del cliente es obligatorio")
    private Long clienteId; // ID del cliente asociado

    @NotNull(message = "La lista de productos no puede estar vacía")
    private List<Long> productosIds; // Lista de IDs de productos en el pedido

    public List<Long> getProductosIds() {
        return productosIds;
    }

    public void setProductosIds(List<Long> productosIds) {
        this.productosIds = productosIds;
    }

    public Long getClienteId() {
        return clienteId;
    }

    public void setClienteId(Long clienteId) {
        this.clienteId = clienteId;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PedidoDTO() {
    }

    public PedidoDTO(Long id, LocalDateTime fechaCreacion, String estado, BigDecimal total, Long clienteId, List<Long> productosIds) {
        this.id = id;
        this.fechaCreacion = fechaCreacion;
        this.estado = estado;
        this.total = total;
        this.clienteId = clienteId;
        this.productosIds = productosIds;
    }
}
