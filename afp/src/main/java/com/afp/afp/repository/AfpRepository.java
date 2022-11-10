package com.afp.afp.repository;

import com.afp.afp.entity.Afp;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AfpRepository extends JpaRepository<Afp,Integer> {
    public List<Afp> findBynombre(String nombre);
}
