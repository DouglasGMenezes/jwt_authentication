package com.douglas.project.jwt_auth_api.infra.exceptions;

public class NotFoundExceptions extends RuntimeException {

    public NotFoundExceptions() {super("Não encontrado.");}

    public NotFoundExceptions(String messege) {super(messege);}
}
