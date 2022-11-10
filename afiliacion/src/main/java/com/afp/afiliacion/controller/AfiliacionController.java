package com.afp.afiliacion.controller;

import com.afp.afiliacion.dto.AfiliacionDTO;
import com.afp.afiliacion.dto.AfiliacionRegistroDTO;
import com.afp.afiliacion.service.AfiliacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/afiliacion")
public class AfiliacionController {

    @Autowired
    private AfiliacionService afiliacionService;

    @PostMapping
    public ResponseEntity<String> SaveAfiliacionClienteAFP(@RequestBody AfiliacionRegistroDTO afiliacionRegistroDTO){
        return new ResponseEntity<>(afiliacionService.registrarAfiliacion(afiliacionRegistroDTO), HttpStatus.CREATED);
    }

    @GetMapping
    public List<AfiliacionDTO> listaAll(){
        return afiliacionService.listAllRelacionesAFPCliente();
    }


}
