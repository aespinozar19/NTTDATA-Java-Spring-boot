package com.afp.afp.controller;

import com.afp.afp.dto.AfpDTO;
import com.afp.afp.service.AfpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/afp")
public class AfpController {

    @Autowired
    private AfpService afpService;

    @GetMapping
    public List<AfpDTO> listarAfp(){
        return afpService.listarAFP();
        //return null;
    }

    @PostMapping
    public ResponseEntity<AfpDTO> guardarAfp(@RequestBody AfpDTO afpDTO){
        return new ResponseEntity<>(afpService.registrarAFP(afpDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AfpDTO> actualizarAfp(@RequestBody AfpDTO afpDTO, @PathVariable(name = "id") Integer id){
        AfpDTO afpDTORespuesta = afpService.actualizarAFP(afpDTO,id);
        return new ResponseEntity<>(afpDTORespuesta, HttpStatus.OK);
    }

    @DeleteMapping("/{id}/{username}")
    public ResponseEntity<String> EliminarAfp(
            @PathVariable(name = "id") Integer id,
            @PathVariable(name = "username") String usuario){
        String respuesta = afpService.eliminarAFP(id, usuario);
        return new ResponseEntity<>(respuesta, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AfpDTO> listarAfpPorId(@PathVariable(name = "id") Integer id){
        return ResponseEntity.ok(afpService.obtenerAFP(id));
    }




}
