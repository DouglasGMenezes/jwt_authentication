package com.douglas.project.jwt_auth_api.infra.exceptions;

public class InvalidUserExceptions extends RuntimeException {

    public InvalidUserExceptions() {super("Usuario invalido ou já existente."); }

    public InvalidUserExceptions(String massege) {super(massege);}
}
