package com.afp.afiliacion.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Table(name = "tbl_cliente_afp")
//@NoArgsConstructor
public class Afiliacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Integer id;

    @Column(name = "idcliente")
    private Integer idCliente;

    @Column(name = "idAFP")
    private Integer idAFP;

    @Column(name = "montodisponible")
    private Double montoDisponible;

    @Column(name = "numerocuenta")
    private String numeroCuenta;

    @Column(name = "Estado")
    private Integer Estado;

    @Column(name = "usuario_registro")
    private String usuarioReg;

    @Column(name = "fecha_registro")
    private Date FechaReg;

    @Column(name = "usuario_actualizacion")
    private String usuarioAct;

    @Column(name = "fecha_actualizacion")
    private Date FechaAct;

}
