package com.vvm.sh.api.modelos;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Classe que representa uma tarefa proveniente do web service
 */
public class TarefaResultado {

    @SerializedName("SaldoCartaoVM")
    public String saldoCartaoVm;


    @SerializedName("Notas")
    public String notas;

    @SerializedName("ActivExecutadas")
    public List<AtividadeExecutadasResultado> atividadesExecutadas;


    @SerializedName("Dados")
    public List<DadosResultado> dados;
}
