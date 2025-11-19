package br.com.fiap.acess_funcionarios_api.dto;

import br.com.fiap.acess_funcionarios_api.enums.PreferenciaAcessibilidade;
import br.com.fiap.acess_funcionarios_api.enums.TipoLimitacao;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FuncionarioResponseDTO {
    private Long id;
    private String nome;
    private String email;
    private TipoLimitacao tipoLimitacao;
    private PreferenciaAcessibilidade preferenciaAcessibilidade;
    private String areaFuncao;

    private String cep;
    private String logradouro;
    private String bairro;
    private String cidade;
    private String uf;
}
