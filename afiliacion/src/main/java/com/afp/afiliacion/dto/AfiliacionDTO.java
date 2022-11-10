package com.afp.afiliacion.dto;

import lombok.Data;

import javax.persistence.Column;
import java.util.Date;

@Data
public class AfiliacionDTO {
    private Integer id;

    private Integer idCliente;
    private Integer idAFP;
    private Double montoDisponible;
    private String numeroCuenta;
    private Integer Estado;

    private String usuarioReg;
    private Date FechaReg;
    private String usuarioAct;
    private Date FechaAct;
}
