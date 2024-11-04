package com.app.gestionPedidos.app.gestionPedidos.dto;

import jakarta.validation.constraints.NotNull;

import java.util.List;

public class ClienteDTO {

    private Long id;

    @NotNull(message = "El nombre es obligatorio")
    private String nombre;

    @NotNull(message = "El email es obligatorio")
    private String email;

    //@NotNull(message = "El telefono es obligatorio")
    private String telefono;

    //@NotNull(message = "La direccion es obligatoria")
    private String direccion;

    //@NotNull(message = "El telefono es obligatorio")
    private String codigoPostal;

    //@NotNull(message = "La ciudad es obligatoria")
    private String ciudad;

    //@NotNull(message = "El pais es obligatorio")
    private String pais;

    private String tipoCliente;  //individual, empresa

    private List<Long> pedidosIds;

    public ClienteDTO() {
    }

    public ClienteDTO(String nombre, String email) {
        this.nombre = nombre;
        this.email = email;
    }

    public ClienteDTO(List<Long> pedidosIds, String tipoCliente, String pais, String ciudad, String codigoPostal, String direccion, String telefono, String email, String nombre, Long id) {
        this.pedidosIds = pedidosIds;
        this.tipoCliente = tipoCliente;
        this.pais = pais;
        this.ciudad = ciudad;
        this.codigoPostal = codigoPostal;
        this.direccion = direccion;
        this.telefono = telefono;
        this.email = email;
        this.nombre = nombre;
        this.id = id;
    }

    public List<Long> getPedidosIds() {
        return pedidosIds;
    }

    public void setPedidosIds(List<Long> pedidosIds) {
        this.pedidosIds = pedidosIds;
    }

    public String getTipoCliente() {
        return tipoCliente;
    }

    public void setTipoCliente(String tipoCliente) {
        this.tipoCliente = tipoCliente;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
