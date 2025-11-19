package br.com.fiap.acess_funcionarios_api.dto;

import br.com.fiap.acess_funcionarios_api.enums.PreferenciaAcessibilidade;
import br.com.fiap.acess_funcionarios_api.enums.TipoLimitacao;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FuncionarioRequestDTO {

    @NotBlank(message = "O nome é obrigatório.")
    @Size(max = 255, message = "O nome deve ter no máximo 255 caracteres.")
    private String nome;

    @NotBlank(message = "O email é obrigatório.")
    @Email(message = "O formato do email é inválido.")
    @Size(max = 255, message = "O email deve ter no máximo 255 caracteres.")
    private String email;

    @NotNull(message = "O tipo de limitação é obrigatório.")
    private TipoLimitacao tipoLimitacao;

    @NotNull(message = "A preferência de acessibilidade é obrigatória.")
    private PreferenciaAcessibilidade preferenciaAcessibilidade;


    @NotBlank(message = "A área de função é obrigatória.")
    @Size(max = 100, message = "A área de função deve ter no máximo 100 caracteres.")
    private String areaFuncao;

    @NotBlank(message = "O CEP é obrigatório para o cadastro de endereço.")
    @Size(min = 8, max = 8, message = "O CEP deve ter exatamente 8 dígitos.")
    @Pattern(regexp = "[0-9]{8}", message = "O CEP deve conter apenas números.")
    private String cep;
}
