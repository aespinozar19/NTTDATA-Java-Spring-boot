package com.afp.afiliacion.service;

import com.afp.afiliacion.entity.Afiliacion;
import com.afp.afiliacion.repository.AfiliacionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;

import static org.mockito.Mockito.when;


class AfiliacionServiceImplTest {

    @Mock
    AfiliacionRepository afiliacionRepository;

    @InjectMocks
    AfiliacionServiceImpl afiliacionServiceImpl;

    private Afiliacion afiliacion;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);

        afiliacion =new Afiliacion();
        afiliacion.setId(new Afiliacion().getId());
        afiliacion.setIdAFP(1);
        afiliacion.setIdCliente(2);
        afiliacion.setNumeroCuenta("Numero cuenta");
        afiliacion.setMontoDisponible(80500.0);
        afiliacion.setUsuarioReg("Usuario registro");

    }

    @Test
    void listAllRelacionesAFPCliente() {
        when(afiliacionRepository.findAll()).thenReturn(Arrays.asList(afiliacion));
    }
}