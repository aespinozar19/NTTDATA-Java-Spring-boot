package com.afp.cliente.service;

import com.afp.cliente.converter.ClienteConverter;
import com.afp.cliente.dto.ClienteDTO;
import com.afp.cliente.entity.Cliente;
import com.afp.cliente.exception.AppException;
import com.afp.cliente.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ClienteServiceImpl implements ClienteService{

    @Autowired
    ClienteRepository clienteRepository;

    @Autowired
    ClienteConverter clienteConverter;


    @Override
    public ClienteDTO saveClient(ClienteDTO clienteDTO) {

        log.info("Iniciando el registro de cliente");
        List<Cliente> listaCliente = clienteRepository.findByDni(clienteDTO.getDni());
        if(listaCliente.size() > 0)
        {
            throw new AppException(HttpStatus.BAD_REQUEST,"Existe un cliente registrado con el DNI " + clienteDTO.getDni());
        }

        ValidarCamposObligatoriosClientes(clienteDTO);

        Cliente clienteEntity = clienteConverter.ConvertDTOtoEntity(clienteDTO);
        clienteEntity.setEstado(1);
        clienteEntity.setFechaRegistro(new Date());

        Cliente nuevoCliente = clienteRepository.save(clienteEntity);
        log.info("finalizando el registro de cliente");
        return clienteConverter.convertEntityToClienteDto(nuevoCliente);
    }

    @Override
    public ClienteDTO updateClient(ClienteDTO clienteDTO, String dni) {

        log.info("Iniciando actualizacion de cliente");
        List<Cliente> listaCliente = clienteRepository.findByDni(clienteDTO.getDni());
        if(listaCliente.size() == 0)
        {
            throw new AppException(HttpStatus.BAD_REQUEST,"NO existe un cliente con el DNI " + dni);
        }
        if (!clienteDTO.getDni().equals(dni))
        {
            throw new AppException(HttpStatus.BAD_REQUEST,"El DNI del objeto no coincide con el DNI de parametro, elimine el cliente con DNI y vuelva a registrarlo");
        }

        ValidarCamposObligatoriosClientes(clienteDTO);

        Cliente cliente = listaCliente.get(0);
        cliente.setFechaModificacion(new Date());
        cliente.setNombres(clienteDTO.getNombres());
        cliente.setApellidos(clienteDTO.getApellidos());
        cliente.setTelefono(clienteDTO.getTelefono());
        cliente.setCorreo(clienteDTO.getCorreo());
        cliente.setUsuarioModificacion(clienteDTO.getUsuarioModificacion());

        Cliente clienteActualizado = clienteRepository.save(cliente);

        return clienteConverter.convertEntityToClienteDto(clienteActualizado);
    }

    @Override
    public List<ClienteDTO> listAllClient() {
        log.info("Iniciando lista de cliente");
        List<Cliente> listaClientes = clienteRepository.findAll();
        List<ClienteDTO> listaClienteDTO = listaClientes.stream().map(entidad ->
                clienteConverter.convertEntityToClienteDto(entidad)).collect(Collectors.toList());
        log.info("Finalizando lista de cliente");
        return listaClienteDTO;
    }

    @Override
    public ClienteDTO listClientByDni(String dni) {

        if (dni.isEmpty())
        {
            throw new AppException(HttpStatus.BAD_REQUEST,"Ingrese un numero de DNI");
        }

        Cliente cliente = clienteRepository.findByDni(dni).get(0);


        return clienteConverter.convertEntityToClienteDto(cliente);
    }

    @Override
    public String deleteClient(String dni, String usuario) {
        log.info("Iniciando eliminacion de cliente");
        List<Cliente> listaCliente = clienteRepository.findByDni(dni);
        if(listaCliente.size() == 0)
        {
            throw new AppException(HttpStatus.BAD_REQUEST,"NO existe un cliente con el DNI " + dni);
        }

        Cliente cliente = listaCliente.get(0);
        cliente.setFechaModificacion(new Date());
        cliente.setUsuarioModificacion(usuario);
        cliente.setEstado(0);

        Cliente clienteActualizado = clienteRepository.save(cliente);

        return "Registro eliminado";
    }

    private void ValidarCamposObligatoriosClientes(ClienteDTO clienteDTO)
    {
        if(clienteDTO.getNombres().isEmpty())
            throw new AppException(HttpStatus.BAD_REQUEST,"Ingrese los nombres del cliente");

        if(clienteDTO.getApellidos().isEmpty())
            throw new AppException(HttpStatus.BAD_REQUEST,"Ingrese los apellidos del cliente");

        if(clienteDTO.getCorreo().isEmpty())
            throw new AppException(HttpStatus.BAD_REQUEST,"Ingrese el correo del cliente");

        if(clienteDTO.getTelefono().isEmpty())
            throw new AppException(HttpStatus.BAD_REQUEST,"Ingrese el telefono del cliente");

        if(clienteDTO.getDni().isEmpty())
            throw new AppException(HttpStatus.BAD_REQUEST,"Ingrese el DNI del cliente");


    }
}
