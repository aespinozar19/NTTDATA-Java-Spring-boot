package com.afp.afp.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "tbl_afp", uniqueConstraints = {@UniqueConstraint(columnNames = {"nombre"})})
public class Afp {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Integer id;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "estado")
    private Integer estado;

    @Column(name = "usuario_registro")
    private String usuarioReg;

    @Column(name = "fecha_registro")
    private Date FechaReg;

    @Column(name = "usuario_actualizacion")
    private String usuarioAct;

    @Column(name = "fecha_actualizacion")
    private Date FechaAct;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }

    public String getUsuarioReg() {
        return usuarioReg;
    }

    public void setUsuarioReg(String usuarioReg) {
        this.usuarioReg = usuarioReg;
    }

    public Date getFechaReg() {
        return FechaReg;
    }

    public void setFechaReg(Date fechaReg) {
        FechaReg = fechaReg;
    }

    public String getUsuarioAct() {
        return usuarioAct;
    }

    public void setUsuarioAct(String usuarioAct) {
        this.usuarioAct = usuarioAct;
    }

    public Date getFechaAct() {
        return FechaAct;
    }

    public void setFechaAct(Date fechaAct) {
        FechaAct = fechaAct;
    }

    public Afp() {
        super();
    }

    public Afp(Integer id, String nombre, Integer estado, String usuarioReg, Date fechaReg, String usuarioAct, Date fechaAct) {
        this.id = id;
        this.nombre = nombre;
        this.estado = estado;
        this.usuarioReg = usuarioReg;
        this.FechaReg = fechaReg;
        this.usuarioAct = usuarioAct;
        this.FechaAct = fechaAct;
    }

}
