package com.afp.cliente.dto;

import lombok.Data;
import java.util.Date;

@Data
public class ClienteDTO {

    private Integer clienteId;
    private String nombres;
    private String apellidos;
    private String dni;
    private String telefono;
    private String correo;
    private Integer estado;
    private String usuarioRegistro;
    private Date fechaRegistro;
    private String usuarioModificacion;
    private Date fechaModificacion;

}
