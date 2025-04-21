package com.douglas.project.jwt_auth_api.infra.exceptions;

public class ErrorTokenGenerateException extends RuntimeException {

    public ErrorTokenGenerateException() {super("Erro na geração do token.");}

    public ErrorTokenGenerateException(String message) {super(message);}
}
