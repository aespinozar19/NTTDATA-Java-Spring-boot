package com.afp.afiliacion.dto;
import lombok.Data;

@Data
public class AfiliacionRegistroDTO {
    private String DNICliente;
    private Integer idAFP;
    private Double monto;
    private String numeroCuenta;
    private String usuarioReg;
}
