package com.vvm.sh.servicos.tipos.recarregar;

import android.app.Activity;

import com.vvm.sh.baseDados.VvmshBaseDados;
import com.vvm.sh.baseDados.entidades.Atualizacao;
import com.vvm.sh.baseDados.entidades.TipoAtividadePlaneavel;
import com.vvm.sh.repositorios.CarregamentoTiposRepositorio;
import com.vvm.sh.servicos.tipos.TipoAtividadesPlaneaveisAsyncTask;
import com.vvm.sh.util.constantes.Sintaxe;
import com.vvm.sh.util.metodos.MensagensUtil;

import java.util.List;

public class RecarregarTipoAtividadesPlaneaveisAsyncTask  extends TipoAtividadesPlaneaveisAsyncTask {

    private MensagensUtil dialogo;

    public RecarregarTipoAtividadesPlaneaveisAsyncTask(Activity activity, VvmshBaseDados vvmshBaseDados, CarregamentoTiposRepositorio repositorio) {
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
    protected void inserirRegistoBd(Atualizacao atualizacao, List<TipoAtividadePlaneavel> dadosNovos, List<TipoAtividadePlaneavel> dadosAlterados) {
        repositorio.recarregarTipoAtividadesPlaneaveis(atualizacao, dadosNovos, dadosAlterados);
    }
}
