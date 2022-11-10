package com.afp.cliente.exception;

import com.afp.cliente.dto.ErrorDetalles;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@ControllerAdvice
public class GlobalExceptionController {

    @ExceptionHandler(AppException.class)
    public ResponseEntity<ErrorDetalles> manejarAppException(
            AppException exception1, WebRequest webRequest){
        ErrorDetalles errorDetalles = new ErrorDetalles(new Date(),exception1.getMensaje().toString(),
                webRequest.getDescription(false) );
        return new ResponseEntity<>(errorDetalles, exception1.getEstado());
    }

}
