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
                // 1. Tratamento de erros HTTP (4xx e 5xx)
                .onStatus(HttpStatusCode::isError, response -> {
                    // Se a ViaCEP retornar um 404/400, lançamos nossa exceção para o service
                    return Mono.error(new CepNotFoundException("Falha ao consultar CEP na API externa. Status: " + response.statusCode()));
                })
                // 2. Mapeamento do Corpo
                .bodyToMono(ViaCepResponseDTO.class)
                // 3. Bloqueio para retornar o objeto (se der erro de conexão, lança a exceção de rede aqui)
                .block();

    }

}
