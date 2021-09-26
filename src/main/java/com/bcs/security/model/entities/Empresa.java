package com.bcs.security.model.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

@Schema(description = "Empresa Model")
@Entity
@Table(name = "empresas")
public class Empresa implements Serializable {

    public static long getSerialversionuid() {
        return serialVersionUID;
    }
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Schema(description = "RUC de la Empresa")
    @NotNull( message = "{empresas.ruc.notnull}")
    @Size(min = 11, max = 11, message = "{empresas.ruc.size}")
    @Column(name="ruc", nullable = true, length= 20)
    private String ruc;

    @Schema(description = "Razón Social de la Empresa")
    @NotNull( message = "{empresas.razonsocial.notnull}")
    @Size(min = 1, max = 500, message = "{empresas.razonsocial.size}")
    @Column(name="razonsocial", nullable = true, length= 500)
    private String razonsocial;

    @Schema(description = "Descripción de la Empresa")
    @Size(max = 500, message = "{empresas.descripcion.size}")
    @Column(name="descripcion", nullable = true)
    private String descripcion;

    @Schema(description = "Teléfono de la Empresa")
    @Size(max = 45, message = "{empresas.telefono.size}")
    @Column(name="telefono", nullable = true, length= 500)
    private String telefono;

    @Schema(description = "Dirección de la Empresa")
    @Size(max = 500, message = "{empresas.direccion.size}")
    @Column(name="direccion", nullable = true, length= 500)
    private String direccion;

    @Schema(description = "Email de la Empresa")
    @Size(max = 500, message = "{empresas.email.size}")
    @Email(message = "{empresas.email.email}")
    @Column(name="email", nullable = true, length= 500)
    private String email;

    @Schema(description = "ID User Padre")
    @Column(name="user_id", nullable = true)
    private Long userId;

    @Schema(description = "Estado de la Empresa")
    @Column(name="activo", nullable = true)
    private Integer activo;

    @Schema(description = "Borrado Lógico de la Empresa")
    @Column(name="borrado", nullable = true)
    private Integer borrado;

    @Schema(description = "Fecha de Creación del Registro")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @Column(name="created_at", nullable = true)
    private LocalDateTime createdAt;

    @Schema(description = "Fecha de Update del Registro")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @Column(name="updated_at", nullable = true)
    private LocalDateTime updatedAd;

    public Empresa() {
    }

    public Empresa(Long id, String ruc, String razonsocial, String descripcion, String telefono, String direccion, String email, Long userId, Integer activo, Integer borrado) {
        this.id = id;
        this.ruc = ruc;
        this.razonsocial = razonsocial;
        this.descripcion = descripcion;
        this.telefono = telefono;
        this.direccion = direccion;
        this.email = email;
        this.userId = userId;
        this.activo = activo;
        this.borrado = borrado;
    }

    public Empresa(Long id, String ruc, String razonsocial, String descripcion, String telefono, String direccion, String email, Long userId, Integer activo, Integer borrado, LocalDateTime createdAt, LocalDateTime updatedAd) {
        this.id = id;
        this.ruc = ruc;
        this.razonsocial = razonsocial;
        this.descripcion = descripcion;
        this.telefono = telefono;
        this.direccion = direccion;
        this.email = email;
        this.userId = userId;
        this.activo = activo;
        this.borrado = borrado;
        this.createdAt = createdAt;
        this.updatedAd = updatedAd;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRuc() {
        return ruc;
    }

    public void setRuc(String ruc) {
        this.ruc = ruc;
    }

    public String getRazonsocial() {
        return razonsocial;
    }

    public void setRazonsocial(String razonsocial) {
        this.razonsocial = razonsocial;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Integer getActivo() {
        return activo;
    }

    public void setActivo(Integer activo) {
        this.activo = activo;
    }

    public Integer getBorrado() {
        return borrado;
    }

    public void setBorrado(Integer borrado) {
        this.borrado = borrado;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAd() {
        return updatedAd;
    }

    public void setUpdatedAd(LocalDateTime updatedAd) {
        this.updatedAd = updatedAd;
    }
}
