package com.afp.cliente.controller;

import com.afp.cliente.dto.ClienteDTO;
import com.afp.cliente.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cliente")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping
    public List<ClienteDTO> listaAll(){
        return clienteService.listAllClient();
        //return null;
    }

    @PostMapping
    public ResponseEntity<ClienteDTO> SaveCliente(@RequestBody ClienteDTO clienteDTO){
        return new ResponseEntity<>(clienteService.saveClient(clienteDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{dni}")
    public ResponseEntity<ClienteDTO> UpdateCliente(@RequestBody ClienteDTO clienteDTO, @PathVariable(name = "dni") String dni){
        ClienteDTO clienteDTORespuesta = clienteService.updateClient(clienteDTO, dni);
        return new ResponseEntity<>(clienteDTORespuesta, HttpStatus.OK);
    }

    @GetMapping("/{dni}")
    public ResponseEntity<ClienteDTO> listarAfpPorId(@PathVariable(name = "dni") String dni){
        return ResponseEntity.ok(clienteService.listClientByDni(dni));
    }

    @DeleteMapping("/{dni}/{username}")
    public ResponseEntity<String> EliminarAfp(
            @PathVariable(name = "dni") String dni,
            @PathVariable(name = "username") String usuario){
        String respuesta = clienteService.deleteClient(dni, usuario);
        return new ResponseEntity<>(respuesta, HttpStatus.OK);
    }

}
