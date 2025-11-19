package br.com.fiap.acess_funcionarios_api.enums;

import lombok.Getter;

@Getter
public enum PreferenciaAcessibilidade {
    LETRAS_GRANDES("Letras Grandes"),
    CONTRASTE_ALTO("Contraste Alto"),
    VOZ("Comandos de Voz"),
    LEITOR_TELA("Leitor de Tela"),
    OUTRO("Outra Preferência");

    private final String descricao;

    PreferenciaAcessibilidade(String descricao) {
        this.descricao = descricao;
    }
}
