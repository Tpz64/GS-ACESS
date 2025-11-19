package br.com.fiap.acess_funcionarios_api.dto;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record ViaCepResponseDTO (
        String cep,
        String logradouro,
        String complemento,
        String bairro,
        String localidade,
        String uf,
        boolean erro
){
    public String cidade() {
        return this.localidade;
    }
}