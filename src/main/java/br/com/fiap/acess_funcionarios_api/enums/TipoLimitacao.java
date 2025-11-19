package br.com.fiap.acess_funcionarios_api.enums;

import lombok.Getter;

@Getter
public enum TipoLimitacao {
    FISICA("Física"),
    MOTORA("Motora"),
    SENSORIAL("Sensorial"),
    COGNITIVA("Cognitiva"),
    MULTIPLA("Múltipla");

    private final String descricao;

    TipoLimitacao(String descricao) {
        this.descricao = descricao;
    }
}
