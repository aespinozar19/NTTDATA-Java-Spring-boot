package com.afp.afp.service;

import com.afp.afp.dto.AfpDTO;

import java.util.List;

public interface AfpService {

    public AfpDTO registrarAFP(AfpDTO afpDTO);

    public List<AfpDTO> listarAFP();

    public AfpDTO obtenerAFP(Integer id);

    public AfpDTO actualizarAFP(AfpDTO afpDTO, Integer id);

    public String eliminarAFP(Integer id, String usuario);


}
