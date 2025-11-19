package br.com.fiap.acess_funcionarios_api.exception;


public class CepNotFoundException extends RuntimeException {
    public CepNotFoundException(String message) {
        super(message);
    }
}
