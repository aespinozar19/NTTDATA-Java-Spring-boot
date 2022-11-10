package com.afp.afiliacion.repository;

import com.afp.afiliacion.entity.Afiliacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.stereotype.Repository;

@Repository
public interface AfiliacionRepository extends JpaRepository<Afiliacion,Integer> {

    @Procedure("registrar_cliente_afp")
    String RegistrarAfiliacion(String DNICliente, Integer idAFP,
                               Double montoDisponible, String numeroCuenta,
                               String usuarioRegmodel);

    @Procedure("registrar_movimiento")
    String RegistrarRetiro(String DNICliente, Integer idAFP, Double monto, String usuario);

}
