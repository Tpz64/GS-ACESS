package br.com.fiap.acess_funcionarios_api.model;

import br.com.fiap.acess_funcionarios_api.enums.PreferenciaAcessibilidade;
import br.com.fiap.acess_funcionarios_api.enums.TipoLimitacao;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="funcionario")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Funcionario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false, unique = true)
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_limitacao", nullable = false)
    private TipoLimitacao tipoLimitacao;

    @Enumerated(EnumType.STRING)
    @Column(name = "preferencia_acessibilidade", nullable = false)
    private PreferenciaAcessibilidade preferenciaAcessibilidade;

    @Column(name = "area_funcao", nullable = false)
    private String areaFuncao;

    private String cep;
    private String logradouro;
    private String bairro;
    private String cidade;
    private String uf;
}
