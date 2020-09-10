package com.vvm.sh.api.modelos.pedido;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class IAvaliacaoRiscosAnterior {

    @SerializedName("processoProdutivo")
    public String processoProdutivo;

    @SerializedName("equipamentos")
    public List<String> equipamentos;

    @SerializedName("levantamentosRisco")
    public List<ILevantamentoRisco> levantamentosRisco;


    //TODO: Completar

//    @SerializedName("Vulnerabilidades")
//    public List<IVulnerabilidade> vulnerabilidades;
//
//    @SerializedName("Checklist")
//    public List<IChecklist> checklist;
//
//    @SerializedName("RelatorioPlanoAcao")
//    public List<ILevantamentoRisco> relatorioPlanoAcao;
//


    public class ILevantamentoRisco{

        @SerializedName("perigo")
        public String perigo;

        @SerializedName("tarefa")
        public String tarefa;

        @SerializedName("categoriasProfissionais")
        public List<ICategoriaProfissional> categoriasProfissionais;

        @SerializedName("riscos")
        public List<IRisco> riscos;
    }


    public class ICategoriaProfissional{

        @SerializedName("id")
        public String id;

        @SerializedName("h")
        public String numeroHomens;

        @SerializedName("m")
        public String numeroMulheres;
    }


    public class IRisco{

        @SerializedName("nc")
        public String nc;

        @SerializedName("nd")
        public String nd;

        @SerializedName("ne")
        public String ne;

        @SerializedName("idRiscoEspecifico")
        public String idRiscoEspecifico;

        @SerializedName("idRisco")
        public String idRisco;

        @SerializedName("consequencias")
        public String consequencias;


        @SerializedName("idsMedidasExistentes")
        public List<String> idsMedidasExistentes;

        @SerializedName("idsMedidasRecomendadas")
        public List<String> idsMedidasRecomendadas;

        @SerializedName("idsMedidasPlanoAcao")
        public List<String> idsMedidasPlanoAcao;
    }
}
