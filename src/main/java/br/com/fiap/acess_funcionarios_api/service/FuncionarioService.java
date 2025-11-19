package br.com.fiap.acess_funcionarios_api.service;

import br.com.fiap.acess_funcionarios_api.client.ViaCepClient;
import br.com.fiap.acess_funcionarios_api.dto.FuncionarioRequestDTO;
import br.com.fiap.acess_funcionarios_api.dto.FuncionarioResponseDTO;
import br.com.fiap.acess_funcionarios_api.dto.ViaCepResponseDTO;
import br.com.fiap.acess_funcionarios_api.exception.CepNotFoundException;
import br.com.fiap.acess_funcionarios_api.exception.NotFoundException;
import br.com.fiap.acess_funcionarios_api.model.Funcionario;
import br.com.fiap.acess_funcionarios_api.repository.FuncionarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FuncionarioService {
    private final FuncionarioRepository funcionarioRepository;
    private final ViaCepClient viaCepClient;

    public FuncionarioResponseDTO cadastrar(FuncionarioRequestDTO requestDTO) {
        ViaCepResponseDTO cepResponse = viaCepClient.buscaEnderecoPorCep(requestDTO.getCep());

        if (cepResponse == null || cepResponse.erro() || cepResponse.localidade() == null) {
            throw new CepNotFoundException("CEP inválido ou não encontrado: " + requestDTO.getCep());
        }

        Funcionario funcionario = mapRequestToEntity(requestDTO, cepResponse);

        Funcionario savedFuncionario = funcionarioRepository.save(funcionario);

        return mapEntityToResponse(savedFuncionario);
    }


    public FuncionarioResponseDTO buscarPorId(Long id) {
        Funcionario funcionario = funcionarioRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Funcionário não encontrado com ID: " + id));

        return mapEntityToResponse(funcionario);
    }


    public List<FuncionarioResponseDTO> listarTodos() {
        return funcionarioRepository.findAll().stream()
                .map(this::mapEntityToResponse)
                .collect(Collectors.toList());
    }


    public FuncionarioResponseDTO atualizar(Long id, FuncionarioRequestDTO requestDTO) {
        Funcionario funcionarioExistente = funcionarioRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Funcionário não encontrado com ID: " + id));

        ViaCepResponseDTO cepResponse = viaCepClient.buscaEnderecoPorCep(requestDTO.getCep());
        if (cepResponse == null || cepResponse.erro()) {
            throw new CepNotFoundException("Novo CEP inválido ou não encontrado: " + requestDTO.getCep());
        }

        updateEntityFromRequest(funcionarioExistente, requestDTO, cepResponse);

        Funcionario updatedFuncionario = funcionarioRepository.save(funcionarioExistente);

        return mapEntityToResponse(updatedFuncionario);
    }


    public void deletar(Long id) {
        if (!funcionarioRepository.existsById(id)) {
            throw new NotFoundException("Funcionário não encontrado com ID: " + id);
        }
        funcionarioRepository.deleteById(id);
    }

    private Funcionario mapRequestToEntity(FuncionarioRequestDTO dto, ViaCepResponseDTO cep) {
        Funcionario f = new Funcionario();
        f.setNome(dto.getNome());
        f.setEmail(dto.getEmail());
        f.setTipoLimitacao(dto.getTipoLimitacao());
        f.setPreferenciaAcessibilidade(dto.getPreferenciaAcessibilidade());
        f.setAreaFuncao(dto.getAreaFuncao());

        f.setCep(cep.cep());
        f.setLogradouro(cep.logradouro());
        f.setBairro(cep.bairro());
        f.setCidade(cep.localidade());
        f.setUf(cep.uf());
        return f;
    }

    private Funcionario updateEntityFromRequest(Funcionario funcionario, FuncionarioRequestDTO dto, ViaCepResponseDTO cep) {
        funcionario.setNome(dto.getNome());
        funcionario.setEmail(dto.getEmail());
        funcionario.setTipoLimitacao(dto.getTipoLimitacao());
        funcionario.setPreferenciaAcessibilidade(dto.getPreferenciaAcessibilidade());
        funcionario.setAreaFuncao(dto.getAreaFuncao());

        funcionario.setCep(cep.cep());
        funcionario.setLogradouro(cep.logradouro());
        funcionario.setBairro(cep.bairro());
        funcionario.setCidade(cep.localidade());
        funcionario.setUf(cep.uf());
        return funcionario;
    }

    private FuncionarioResponseDTO mapEntityToResponse(Funcionario funcionario) {
        FuncionarioResponseDTO response = new FuncionarioResponseDTO();
        response.setId(funcionario.getId());
        response.setNome(funcionario.getNome());
        response.setEmail(funcionario.getEmail());
        response.setTipoLimitacao(funcionario.getTipoLimitacao());
        response.setPreferenciaAcessibilidade(funcionario.getPreferenciaAcessibilidade());
        response.setAreaFuncao(funcionario.getAreaFuncao());

        response.setCep(funcionario.getCep());
        response.setLogradouro(funcionario.getLogradouro());
        response.setBairro(funcionario.getBairro());
        response.setCidade(funcionario.getCidade());
        response.setUf(funcionario.getUf());
        return response;
    }
}