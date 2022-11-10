package com.afp.afiliacion.controller;

import com.afp.afiliacion.dto.AfiliacionRegistroDTO;
import com.afp.afiliacion.dto.SolicitudRetiroDTO;
import com.afp.afiliacion.service.AfiliacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/solicitudes")
public class SolicitudRetiroController {

    @Autowired
    private AfiliacionService afiliacionService;

    @PostMapping
    public ResponseEntity<String> RegisterRetiro(@RequestBody SolicitudRetiroDTO solicitudRetiroDTO){
        String respuesta = afiliacionService.registrarRetiro(solicitudRetiroDTO);
        return new ResponseEntity<>(respuesta, HttpStatus.OK);
    }

}
