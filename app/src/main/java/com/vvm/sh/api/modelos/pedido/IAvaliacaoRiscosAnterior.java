package com.vvm.sh.api.modelos.pedido;

import com.google.gson.annotations.SerializedName;
import com.vvm.sh.util.constantes.Identificadores;

import java.util.List;

public class IAvaliacaoRiscosAnterior {

    @SerializedName("processoProdutivo")
    public String processoProdutivo;

    @SerializedName("equipamentos")
    public List<String> equipamentos;

    @SerializedName("levantamentosRisco")
    public List<ILevantamentoRisco> levantamentosRisco;


    @SerializedName("Vulnerabilidades")
    public List<IVulnerabilidade> vulnerabilidades;

    @SerializedName("Checklist")
    public List<IChecklist> checklist;

    @SerializedName("RelatorioPlanoAcao")
    public List<Integer> relatorioPlanoAcao;



    public class IVulnerabilidade{

        @SerializedName("idVulnerabilidade")
        public int id;

        @SerializedName("quantidadeMulheres")
        public int mulheres;

        @SerializedName("quantidadeHomens")
        public int homens;

        @SerializedName("categoriasProfissionaisHomens")
        public List<String> categoriasProfissionaisHomens;

        @SerializedName("categoriasProfissionaisMulheres")
        public List<String> categoriasProfissionaisMulheres;
    }



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

    }


    public class IChecklist{

        @SerializedName("idChecklist")
        public String id;

        @SerializedName("versaoChecklist")
        public String versao;

        @SerializedName("dadosChecklist")
        public List<IArea> areas;

    }

    public class IArea{

        @SerializedName("area")
        public String area;

        @SerializedName("descricaoArea")
        public String descricao;

        @SerializedName("idArea")
        public String id;

        @SerializedName("dadosArea")
        public List<ISeccao> seccoes;

    }


    public class ISeccao{

        @SerializedName("idSeccao")
        public String id;

        @SerializedName("dados")
        public List<IItem> itens;

    }


    public class IItem{

        @SerializedName("idItem")
        public String id;

        @SerializedName("resposta")
        public String resposta;

        @SerializedName("nr")
        public String nr;

        @SerializedName("idUT1")
        public String idUT1;

        @SerializedName("idCategoriasRiscoUT_1")
        public String idCategoriasRiscoUT_1;

        @SerializedName("localRiscoA_ut_1")
        public boolean localRiscoA_ut_1;

        @SerializedName("localRiscoB_ut_1")
        public boolean localRiscoB_ut_1;

        @SerializedName("localRiscoC_ut_1")
        public boolean localRiscoC_ut_1;

        @SerializedName("localRiscoD_ut_1")
        public boolean localRiscoD_ut_1;

        @SerializedName("localRiscoE_ut_1")
        public boolean localRiscoE_ut_1;

        @SerializedName("localRiscoF_ut_1")
        public boolean localRiscoF_ut_1;


        @SerializedName("idUT2")
        public String idUT2;

        @SerializedName("idCategoriasRiscoUT_2")
        public String idCategoriasRiscoUT_2 = Identificadores.VALOR_0;

        @SerializedName("localRiscoA_ut_2")
        public boolean localRiscoA_ut_2;

        @SerializedName("localRiscoB_ut_2")
        public boolean localRiscoB_ut_2;

        @SerializedName("localRiscoC_ut_2")
        public boolean localRiscoC_ut_2;

        @SerializedName("localRiscoD_ut_2")
        public boolean localRiscoD_ut_2;

        @SerializedName("localRiscoE_ut_2")
        public boolean localRiscoE_ut_2;

        @SerializedName("localRiscoF_ut_2")
        public boolean localRiscoF_ut_2;



/*
                "ci": "",
                "qsi": "",
                "rai": "",
                "h": "",
                "si": "",
                "dataCarregamento": "",
                "idTipoExtintor": "",
                "dataValidade": "",
                "codigoExtintor": "",
                "iluminacaoNatural": "",
                "postoMedicao": "",
                "normaIluminacao": "",
                "medidasTermicas": [],
                "humRel": "",
                "postoTrabalho": "",
                "medidasIluminacao": [],
                "iluminacaoArtificial": "",
                "iluminacaoMista": "",
                "tempOper": "",
                "nomeTrabalhador": "",
                "fotos": []
        */

    }
}
