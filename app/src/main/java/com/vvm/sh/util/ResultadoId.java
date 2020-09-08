package com.vvm.sh.util;

import com.vvm.sh.util.constantes.Identificadores;
import com.vvm.sh.util.constantes.Sintaxe;

import java.util.ArrayList;
import java.util.List;

public enum ResultadoId {

    EMAIL(Identificadores.Resultados.ID_EMAIL, Sintaxe.Palavras.EMAIL),

    ANOMALIA_CLIENTE(Identificadores.Resultados.ID_ANOMALIA_CLIENTE, Sintaxe.Palavras.ANOMALIA_CLIENTE),

    ATIVIDADE_PENDENTE(Identificadores.Resultados.ID_ATIVIDADE_PENDENTE, Sintaxe.Palavras.ATIVIDADE_PENDENTE),

    CROSS_SELLING(Identificadores.Resultados.ID_CROSS_SELLING, Sintaxe.Palavras.CROSS_SELLING),

    OCORRENCIA(Identificadores.Resultados.ID_OCORRENCIA, Sintaxe.Palavras.OCORRENCIA),

    SINISTRALIDADE(Identificadores.Resultados.ID_SINISTRALIDADE, Sintaxe.Palavras.SINISTRALIDADE);


    private final int value;
    private final String descricao;

    ResultadoId(final int newValue, final String descricao) {
        value = newValue;
        this.descricao = descricao;
    }

    public int getValue() { return value; }

    public String obterDescricao() { return descricao; }

    @Override
    public String toString() {
        return value + ". " + descricao;
    }


    public static String obterDescricao(int id) {

        List<ResultadoId> registos = new ArrayList<>();
        registos.add(EMAIL);
        registos.add(ANOMALIA_CLIENTE);
        registos.add(ATIVIDADE_PENDENTE);
        registos.add(CROSS_SELLING);
        registos.add(OCORRENCIA);


        for (ResultadoId item: registos) {

            if(item.value == id){
                return item.toString();
            }
        }

        return id + ". Sem descricao" ;
    }
}
