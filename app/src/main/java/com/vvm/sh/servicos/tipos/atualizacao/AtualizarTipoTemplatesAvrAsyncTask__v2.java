package com.vvm.sh.servicos.tipos.atualizacao;

import com.vvm.sh.baseDados.VvmshBaseDados;
import com.vvm.sh.baseDados.entidades.Atualizacao;
import com.vvm.sh.baseDados.entidades.TipoTemplateAvrLevantamento;
import com.vvm.sh.baseDados.entidades.TipoTemplateAvrRisco;
import com.vvm.sh.baseDados.entidades.TipoTemplatesAVRMedidaRisco;
import com.vvm.sh.repositorios.CarregamentoTiposRepositorio;
import com.vvm.sh.servicos.tipos.TipoTemplatesAvrAsyncTask;
import com.vvm.sh.ui.transferencias.adaptadores.OnTransferenciaListener;
import com.vvm.sh.util.AtualizacaoUI_;

import java.util.List;

public class AtualizarTipoTemplatesAvrAsyncTask__v2 extends TipoTemplatesAvrAsyncTask {

    private OnTransferenciaListener listener;

    public AtualizarTipoTemplatesAvrAsyncTask__v2(OnTransferenciaListener listener, VvmshBaseDados vvmshBaseDados, CarregamentoTiposRepositorio repositorio){
        super(vvmshBaseDados, repositorio);

        this.listener = listener;
    }



    @Override
    protected void onPostExecute(List<Object> iTipoListagems) {

        listener.atualizarTransferencia(new AtualizacaoUI_(AtualizacaoUI_.Estado.PROCESSAMENTO_TEMPLATE_AVALIACAO_RISCOS, erro));

        AtualizarTipoChecklistAsyncTask servico = new AtualizarTipoChecklistAsyncTask(listener, vvmshBaseDados, repositorio);
        servico.execute(iTipoListagems);

    }

    @Override
    protected void atualizarUI(int index, int limite, Atualizacao atualizacao) {
        listener.atualizarTransferencia(new AtualizacaoUI_(AtualizacaoUI_.Estado.PROCESSAMENTO_TEMPLATE_AVALIACAO_RISCOS, index, limite, obterDescricaoApi(atualizacao) + " " + atualizacao.descricao));

    }

    @Override
    protected void inserirRegistoBd(Atualizacao atualizacaoLevantamento,
                                    List<TipoTemplateAvrLevantamento> dadosNovosLevantamento, List<TipoTemplateAvrLevantamento> dadosAlteradosLevantamento,
                                    Atualizacao atualizacaoRisco,
                                    List<TipoTemplateAvrRisco> dadosNovosRiscos, List<TipoTemplateAvrRisco> dadosAlteradosRiscos,
                                    List<TipoTemplatesAVRMedidaRisco> medidasExistentes, List<TipoTemplatesAVRMedidaRisco> medidasAlteradasExistentes,
                                    List<TipoTemplatesAVRMedidaRisco> medidasRecomendadas, List<TipoTemplatesAVRMedidaRisco> medidasAlteradasRecomendadas) {

        repositorio.atualizarTipoTemplateAvr(
                atualizacaoLevantamento, dadosNovosLevantamento, dadosAlteradosLevantamento,
                atualizacaoRisco, dadosNovosRiscos, dadosAlteradosRiscos,
                medidasExistentes, medidasAlteradasExistentes, medidasRecomendadas, medidasAlteradasRecomendadas);

    }
}
