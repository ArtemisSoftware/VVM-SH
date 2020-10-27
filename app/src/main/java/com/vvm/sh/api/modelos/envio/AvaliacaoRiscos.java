package com.vvm.sh.api.modelos.envio;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AvaliacaoRiscos {

    @SerializedName("Checklist")
    public List<Checklist> checklist;

    @SerializedName("Vulnerabilidades")
    public List<TrabalhadorVulneravel> trabalhadoresVulneraveis;

    @SerializedName("equipamentos")
    public List<String> equipamentos;

    @SerializedName("processoProdutivo")
    public String processoProdutivo;

    @SerializedName("levantamentosRisco")
    public List<Levantamento> levantamentosRisco;

//    RelatorioPlanoAcao
//            capaRelatorio

//				jAvaliacao.put(JsonEnvioIF.RELATORIO_PLANO_ACAO, acessoBd.obterPlanoAcao_AVR(idAtividade));
//				jAvaliacao.put(JsonEnvioIF.CAPA_RELATORIO, acessoBd.obterCapaRelatorio(idTarefa));
}
