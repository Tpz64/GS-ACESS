package br.com.fiap.acess_funcionarios_api.exception;

public class NotFoundException extends RuntimeException{
    // Construtor que recebe a mensagem de erro
    public NotFoundException(String message) {
        super(message);
    }
}
