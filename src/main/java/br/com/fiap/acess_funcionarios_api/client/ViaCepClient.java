package br.com.fiap.acess_funcionarios_api.client;

import br.com.fiap.acess_funcionarios_api.dto.ViaCepResponseDTO;
import br.com.fiap.acess_funcionarios_api.exception.CepNotFoundException;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class ViaCepClient {

    private final WebClient webClient;

    public ViaCepClient(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder
                .baseUrl("https://viacep.com.br/ws/")
                .build();
    }

    public ViaCepResponseDTO buscaEnderecoPorCep(String cep) {
        return this.webClient.get()
                .uri("/{cep}/json/", cep)
                .retrieve()
                .onStatus(HttpStatusCode::isError, response -> {
                    return Mono.error(new CepNotFoundException("Falha ao consultar CEP na API externa. Status: " + response.statusCode()));
                })
                .bodyToMono(ViaCepResponseDTO.class)
                .block();

    }

}
