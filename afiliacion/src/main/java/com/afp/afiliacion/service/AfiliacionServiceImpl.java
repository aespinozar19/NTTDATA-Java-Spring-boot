package com.afp.afiliacion.service;

import com.afp.afiliacion.converter.AfiliacionConverter;
import com.afp.afiliacion.dto.AfiliacionDTO;
import com.afp.afiliacion.dto.AfiliacionRegistroDTO;
import com.afp.afiliacion.dto.SolicitudRetiroDTO;
import com.afp.afiliacion.entity.Afiliacion;
import com.afp.afiliacion.repository.AfiliacionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class AfiliacionServiceImpl implements AfiliacionService{

    @Autowired
    AfiliacionRepository afiliacionRepository;

    @Autowired
    AfiliacionConverter afiliacionConverter;

    @Override
    public String registrarAfiliacion(AfiliacionRegistroDTO afiliacionRegistroDTO) {
        String resultado = "";
        log.info("1");
        log.info(afiliacionRegistroDTO.toString());
        log.info("2");
        String DNICliente = afiliacionRegistroDTO.getDNICliente();
        Integer idAfp = afiliacionRegistroDTO.getIdAFP();
        String numeroCuenta = afiliacionRegistroDTO.getNumeroCuenta();
        String usuario = afiliacionRegistroDTO.getUsuarioReg();
        Double montoDisponible = afiliacionRegistroDTO.getMonto();

        resultado = afiliacionRepository.RegistrarAfiliacion(DNICliente,idAfp,
                montoDisponible, numeroCuenta, usuario);

        if (!resultado.equals("OK")){
            throw new com.afp.afiliacion.exception.AppException(HttpStatus.BAD_REQUEST,resultado);
        }

        return resultado;
    }

    @Override
    public List<AfiliacionDTO> listAllRelacionesAFPCliente() {
        log.info("Iniciando lista");
        List<Afiliacion> lista = afiliacionRepository.findAll();
        log.info(lista.toString());
        List<AfiliacionDTO> listaDTO = lista.stream().map(entidad ->
                afiliacionConverter.ConvertEntitytoDTO(entidad)).collect(Collectors.toList());
        log.info("Finalizando lista");
        return listaDTO;
    }

    @Override
    public String registrarRetiro(SolicitudRetiroDTO solicitudRetiroDTO) {
        String resultado = "OK";
        log.info(solicitudRetiroDTO.getDNICliente());
        log.info(solicitudRetiroDTO.getIdAFP().toString());
        log.info(solicitudRetiroDTO.getMontoRetiro().toString());
        resultado = afiliacionRepository.RegistrarRetiro(solicitudRetiroDTO.getDNICliente(),
                solicitudRetiroDTO.getIdAFP(),solicitudRetiroDTO.getMontoRetiro(),
                solicitudRetiroDTO.getUsuarioRegistro());
        if (!resultado.equals("OK")){
            throw new com.afp.afiliacion.exception.AppException(HttpStatus.BAD_REQUEST,resultado);
        }
        return resultado;
    }
}
