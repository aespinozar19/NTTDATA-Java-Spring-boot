package com.afp.cliente.service;

import com.afp.cliente.dto.ClienteDTO;

import java.util.List;

public interface ClienteService {
    public ClienteDTO saveClient(ClienteDTO clienteDTO);
    public ClienteDTO updateClient(ClienteDTO clienteDTO, String dni);
    public List<ClienteDTO> listAllClient();
    public ClienteDTO listClientByDni(String dni);
    public String deleteClient(String dni, String usuario);
}
