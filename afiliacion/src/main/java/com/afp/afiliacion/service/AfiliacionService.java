package com.afp.afiliacion.service;

import com.afp.afiliacion.dto.AfiliacionDTO;
import com.afp.afiliacion.dto.AfiliacionRegistroDTO;
import com.afp.afiliacion.dto.SolicitudRetiroDTO;
import com.afp.afiliacion.entity.Afiliacion;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;

import java.util.List;

public interface AfiliacionService {

    public String registrarAfiliacion(AfiliacionRegistroDTO afiliacionRegistroDTO);

    public List<AfiliacionDTO> listAllRelacionesAFPCliente();

    public String registrarRetiro(SolicitudRetiroDTO solicitudRetiroDTO);

}
