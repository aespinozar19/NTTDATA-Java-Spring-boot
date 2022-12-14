package com.afp.cliente.repository;

import com.afp.cliente.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ClienteRepository extends JpaRepository<Cliente,Integer> {

    public List<Cliente> findByDni(String dni);

}
