package com.vvm.sh.api.modelos.pedido;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class IPlanoAcao {

    @SerializedName("AnuidadeVigente")
    public String anuidade;

    @SerializedName("TST")
    public String tst;

    @SerializedName("CAP")
    public String cap;

    @SerializedName("Email")
    public String email;

    @SerializedName("OutraAnuidade")
    public String outraAnuidade; //TODO: atencao isto vem como string True

    @SerializedName("ActividadesPlano")
    public List<IAtividadePlano> atividades;



    public class IAtividadePlano{

        @SerializedName("ServID")
        public String servId;

        @SerializedName("DescSimples")
        public String descricaoSimples;

        @SerializedName("Descricao")
        public String descricao;

        @SerializedName("EquipaSST")
        public String equipaSst;

        @SerializedName("SempreNecessario")
        public String sempreNecessario; //TODO: isto ou vem como "" ou como x. Fazer substituicao por boolean

        @SerializedName("DataProg")
        public String dataProgramada;

        @SerializedName("DataExec")
        public String dataExecucao;

        @SerializedName("Reprogramada")
        public int reprogramada; //TODO: verificar isto. Vem a 0

        @SerializedName("OBS")
        public String observacao;

        @SerializedName("FIXO")
        public String posicao; //TODO: criar inteiros para topo, meio e fim ---> opcoes topo, null, fim

        @SerializedName("Anuidade")
        public String anuidade;
    }

}
