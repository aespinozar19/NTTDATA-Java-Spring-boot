package com.afp.afiliacion.converter;

import com.afp.afiliacion.dto.AfiliacionDTO;
import com.afp.afiliacion.entity.Afiliacion;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class AfiliacionConverter {

    public Afiliacion ConvertDTOtoEntity(AfiliacionDTO afiliacionDTO){
        Afiliacion afiliacion = new Afiliacion();
        afiliacion.setId(afiliacionDTO.getId());
        afiliacion.setIdCliente(afiliacionDTO.getIdCliente());
        afiliacion.setIdAFP(afiliacionDTO.getIdAFP());
        afiliacion.setMontoDisponible(afiliacionDTO.getMontoDisponible());
        afiliacion.setNumeroCuenta(afiliacionDTO.getNumeroCuenta());
        afiliacion.setEstado(afiliacionDTO.getEstado());
        afiliacion.setUsuarioReg(afiliacionDTO.getUsuarioReg());
        afiliacion.setFechaReg(afiliacionDTO.getFechaReg());
        afiliacion.setUsuarioAct(afiliacionDTO.getUsuarioAct());
        afiliacion.setFechaAct(afiliacionDTO.getFechaAct());
        return afiliacion;
    }

    public AfiliacionDTO ConvertEntitytoDTO(Afiliacion afiliacion){
        AfiliacionDTO afiliacionDTO = new AfiliacionDTO();

        afiliacionDTO.setId(afiliacion.getId());
        afiliacionDTO.setIdCliente(afiliacion.getIdCliente());
        afiliacionDTO.setIdAFP(afiliacion.getIdAFP());
        afiliacionDTO.setMontoDisponible(afiliacion.getMontoDisponible());
        afiliacionDTO.setNumeroCuenta(afiliacion.getNumeroCuenta());
        afiliacionDTO.setEstado(afiliacion.getEstado());
        afiliacionDTO.setUsuarioReg(afiliacion.getUsuarioReg());
        afiliacionDTO.setFechaReg(afiliacion.getFechaReg());
        afiliacionDTO.setUsuarioAct(afiliacion.getUsuarioAct());
        afiliacionDTO.setFechaAct(afiliacion.getFechaAct());
        return afiliacionDTO;
    }

}
