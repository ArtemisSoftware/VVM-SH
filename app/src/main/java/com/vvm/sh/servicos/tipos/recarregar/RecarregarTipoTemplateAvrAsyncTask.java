package com.vvm.sh.servicos.tipos.recarregar;

import android.app.Activity;

import com.vvm.sh.baseDados.VvmshBaseDados;
import com.vvm.sh.baseDados.entidades.Atualizacao;
import com.vvm.sh.baseDados.entidades.Tipo;
import com.vvm.sh.baseDados.entidades.TipoTemplateAvrLevantamento;
import com.vvm.sh.baseDados.entidades.TipoTemplateAvrRisco;
import com.vvm.sh.baseDados.entidades.TipoTemplatesAVRMedidaRisco;
import com.vvm.sh.repositorios.CarregamentoTiposRepositorio;
import com.vvm.sh.servicos.tipos.TipoAsyncTask;
import com.vvm.sh.servicos.tipos.TipoTemplatesAvrAsyncTask;
import com.vvm.sh.util.constantes.Sintaxe;
import com.vvm.sh.util.metodos.MensagensUtil;

import java.util.List;

public class RecarregarTipoTemplateAvrAsyncTask extends TipoTemplatesAvrAsyncTask {

    private MensagensUtil dialogo;

    public RecarregarTipoTemplateAvrAsyncTask(Activity activity, VvmshBaseDados vvmshBaseDados, CarregamentoTiposRepositorio repositorio) {
        super(vvmshBaseDados, repositorio);

        dialogo = new MensagensUtil(activity);
    }


    @Override
    protected void onPreExecute() {
        dialogo.progresso(true, Sintaxe.Frases.ATUALIZAR_TIPO);
    }


    @Override
    protected void onPostExecute(List<Object> iTipoListagems) {
        dialogo.terminarProgresso();

        if(erro != null){
            dialogo.erro(Sintaxe.Alertas.ERRO_ATUALIZAR  + erro);
        }
        else{
            dialogo.sucesso(Sintaxe.Frases.DADOS_ATUALIZADOS_SUCESSO);
        }
    }

        @Override
    protected void atualizarUI(int index, int limite, Atualizacao atualizacao) {

    }



    @Override
    protected void inserirRegistoBd(Atualizacao atualizacaoLevantamento, List<TipoTemplateAvrLevantamento> dadosNovosLevantamento, List<TipoTemplateAvrLevantamento> dadosAlteradosLevantamento, Atualizacao atualizacaoRisco, List<TipoTemplateAvrRisco> dadosNovosRiscos, List<TipoTemplateAvrRisco> dadosAlteradosRiscos, List<TipoTemplatesAVRMedidaRisco> medidasExistentes, List<TipoTemplatesAVRMedidaRisco> medidasAlteradasExistentes, List<TipoTemplatesAVRMedidaRisco> medidasRecomendadas, List<TipoTemplatesAVRMedidaRisco> medidasAlteradasRecomendadas) {
        repositorio.recarregarTipoTemplateAvr(atualizacaoLevantamento, dadosNovosLevantamento, dadosAlteradosLevantamento, atualizacaoRisco, dadosNovosRiscos, dadosAlteradosRiscos, medidasExistentes, medidasAlteradasExistentes, medidasRecomendadas, medidasAlteradasRecomendadas);
    }
}
