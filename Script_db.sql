CREATE DATABASE `db_nttdata_afp` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
CREATE TABLE `tbl_movimientos` (
  `id` int NOT NULL AUTO_INCREMENT,
  `idClienteAFP` int NOT NULL,
  `idTipoMovimiento` int NOT NULL,
  `monto` decimal(10,2) NOT NULL,
  `saldo` decimal(10,2) NOT NULL,
  `usuario_registro` varchar(50) DEFAULT NULL,
  `fecha_registro` datetime DEFAULT NULL,
  `usuario_actualizacion` varchar(50) DEFAULT NULL,
  `fecha_actualizacion` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
CREATE TABLE `tbl_tipo_movimientos` (
  `id` int NOT NULL,
  `descripcion` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
DELIMITER $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `registrar_movimiento`(IN pDNICliente varchar(8),
 IN pidAFP int,
 IN pmonto decimal(10,2), 
 IN pusuario_registro varchar(50),
 OUT presultado varchar(100))
BEGIN  

 set presultado = 'OK';
 set @idCliente = 0;
 set @porcentaje_retiro_min = 0.5; 
 
 IF  ((select COUNT(*) from db_nttdata_afp.tbl_cliente where estado = 1 and dni = pDNICliente)<>1) THEN 
	set presultado = 'No se encontro un cliente con el DNI'; 
 ELSEIF  ((select count(*) from db_nttdata_afp.tbl_afp where id = pidAFP and estado = 1) = 0) THEN 
	set presultado = 'El AFP no se encuentra registrado';
 ELSE 
    set @idCliente = (select id from db_nttdata_afp.tbl_cliente where estado = 1 and dni = pDNICliente);  
 END IF;
 
 IF ((select count(*) from db_nttdata_afp.tbl_cliente_afp 
	where idCliente = @idCliente  and idAFP = pidAFP and estado = 1) = 0) THEN 
	set presultado = 'El AFP y CLIENTE NO cuentan con una relacion activa';   
 ELSE 
	set @idClienteAfp = (select id  from db_nttdata_afp.tbl_cliente_afp 
		where idCliente = @idCliente and idAFP = pidAFP and estado = 1);
	set @MontoMinimo = (select cast(montoDisponible*@porcentaje_retiro_min as decimal(10,2))  from db_nttdata_afp.tbl_cliente_afp 
		where idCliente = @idCliente and idAFP = pidAFP and estado = 1); 
    
    set @SaldoActual = (select montoDisponible  from db_nttdata_afp.tbl_cliente_afp 
		where idCliente = @idCliente and idAFP = pidAFP and estado = 1); 
    
	
    set @nuevoSaldo = @SaldoActual - pmonto;
    
    
    IF (pmonto > @SaldoActual) THEN 
		set presultado = 'No se puede registrar la solicitud. Monto mayor que el permitido';
	ELSEIF (pmonto < @montoMinimo) THEN  
		set presultado = 'Monto mínimo no cubierto por favor revise el monto mínimo a retirar'; 
	ELSE 
		
		INSERT INTO tbl_movimientos (idClienteAFP,idTipoMovimiento,monto,
			saldo,usuario_registro,fecha_registro) 
		values (@idClienteAfp, 2, pmonto, 
			@nuevoSaldo, pusuario_registro, (select CURDATE()));
		
        IF (@nuevoSaldo = 0) THEN 
			UPDATE db_nttdata_afp.tbl_cliente_afp 
            set montoDisponible = 0,
				usuario_actualizacion = pusuario_registro,
                fecha_actualizacion = (select CURDATE()),
                estado = 0 
			where id = @idClienteAfp;
		ELSE 
			UPDATE db_nttdata_afp.tbl_cliente_afp 
            set montoDisponible = @nuevoSaldo,
				usuario_actualizacion = pusuario_registro,
                fecha_actualizacion = (select CURDATE()) 
			where id = @idClienteAfp;
        END IF;
            
		
    END IF;
    
 END IF;


END$$
DELIMITER ;
DELIMITER $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `registrar_cliente_afp`(IN pDNICliente varchar(8),
 IN pidAFP int,
 IN pmontoDisponible decimal(10,2), 
 IN pnumeroCuenta varchar(20), 
 IN pusuario_registro varchar(50),
 OUT presultado varchar(50))
BEGIN  
 set presultado = 'OK';
 
 IF  ((select count(*) from db_nttdata_afp.tbl_afp where id = pidAFP and estado = 1) = 0) THEN 
	set presultado = 'El AFP no se encuentra registrado';
 ELSEIF ((select count(*) from db_nttdata_afp.tbl_cliente where dni = pDNICliente and estado = 1) = 0) THEN 
	set presultado = 'El CLIENTE no se encuentra registrado'; 
 ELSE 
	set @pidCliente = (select id from db_nttdata_afp.tbl_cliente where dni = pDNICliente);
    IF ((select count(*) from db_nttdata_afp.tbl_cliente_afp where idCliente = @pidCliente and idAFP = pidAFP and estado = 1) = 1) THEN 
		set presultado = 'El AFP y CLIENTE cuentan con una relacion activa';
	 ELSE 		
		insert into db_nttdata_afp.tbl_cliente_afp (idCliente,
			idAFP,
			montoDisponible,
			numeroCuenta,
			estado,
			usuario_registro,
			fecha_registro) 
		values (@pidCliente,
			pidAFP,
			pmontoDisponible,
			pnumeroCuenta,
			1,
			pusuario_registro,
			(select CURDATE()));
	 END IF;
 END IF;
 
 
END$$
DELIMITER ;
