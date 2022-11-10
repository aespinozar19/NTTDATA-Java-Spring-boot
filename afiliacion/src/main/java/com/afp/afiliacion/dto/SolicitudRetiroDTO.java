package com.afp.afiliacion.dto;

import lombok.Data;

@Data
public class SolicitudRetiroDTO {
    private String DNICliente;
    private Integer idAFP;
    private Double montoRetiro;
    private String usuarioRegistro;
}
