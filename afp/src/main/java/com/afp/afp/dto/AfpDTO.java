package com.afp.afp.dto;

import java.util.Date;

public class AfpDTO {


    private Integer id;

    private String nombre;

    private Integer estado;

    private String usuarioReg;

    private Date FechaReg;

    private String usuarioAct;

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

    public AfpDTO() {
        super();
    }

}
