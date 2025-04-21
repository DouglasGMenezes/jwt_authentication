package com.douglas.project.jwt_auth_api.infra.exceptions;

import com.auth0.jwt.exceptions.JWTCreationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestExceptionsHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(NotFoundExceptions.class)
    private ResponseEntity<String> notFoundHandler(NotFoundExceptions notFoundExceptions) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado.");
    }

    @ExceptionHandler(InvalidUserExceptions.class)
    private ResponseEntity<String> invalidUserHandler(InvalidUserExceptions invalidUserExceptions) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Usuario invalido ou já existente.");
    }

    @ExceptionHandler(ErrorTokenGenerateException.class)
    private ResponseEntity<String> errorTokenHandler(ErrorTokenGenerateException errorTokenGenerateException) {
        return ResponseEntity.status(HttpStatus.CREATED).body("Erro na geração do token.");
    }
}
