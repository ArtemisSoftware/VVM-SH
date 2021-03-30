package com.vvm.sh.servicos.tipos.recarregar;

import android.app.Activity;

import com.vvm.sh.baseDados.VvmshBaseDados;
import com.vvm.sh.baseDados.entidades.Atualizacao;
import com.vvm.sh.baseDados.entidades.Tipo;
import com.vvm.sh.repositorios.CarregamentoTiposRepositorio;
import com.vvm.sh.servicos.tipos.TipoAsyncTask;
import com.vvm.sh.servicos.tipos.TipoChecklistAsyncTask;
import com.vvm.sh.util.constantes.Sintaxe;
import com.vvm.sh.util.metodos.MensagensUtil;

import java.util.List;

public class RecarregarTipoAsyncTask extends TipoAsyncTask {

    private MensagensUtil dialogo;

    public RecarregarTipoAsyncTask(Activity activity, VvmshBaseDados vvmshBaseDados, CarregamentoTiposRepositorio repositorio) {
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
    protected void inserirRegistoBd(Atualizacao atualizacao, List<Tipo> dadosNovos, List<Tipo> dadosAlterados) {
        repositorio.recarregarTipo(atualizacao, dadosNovos, dadosAlterados);
    }
}
