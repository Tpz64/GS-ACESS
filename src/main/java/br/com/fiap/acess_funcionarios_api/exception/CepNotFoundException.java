package br.com.fiap.acess_funcionarios_api.exception;


public class CepNotFoundException extends RuntimeException {
    // Construtor que recebe a mensagem de erro
    public CepNotFoundException(String message) {
        super(message);
    }
}
