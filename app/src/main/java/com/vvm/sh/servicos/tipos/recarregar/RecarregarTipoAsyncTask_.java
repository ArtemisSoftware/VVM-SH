package com.vvm.sh.servicos.tipos.recarregar;

import android.content.Context;
import android.database.sqlite.SQLiteConstraintException;
import android.os.AsyncTask;

import com.vvm.sh.api.modelos.pedido.ITipo;
import com.vvm.sh.api.modelos.pedido.ITipoListagem;
import com.vvm.sh.baseDados.VvmshBaseDados;
import com.vvm.sh.baseDados.entidades.Atualizacao;
import com.vvm.sh.baseDados.entidades.Tipo;
import com.vvm.sh.repositorios.CarregamentoTiposRepositorio;
import com.vvm.sh.servicos.tipos.TipoAsyncTask;
import com.vvm.sh.ui.transferencias.adaptadores.OnTransferenciaListener;
import com.vvm.sh.util.constantes.Sintaxe;
import com.vvm.sh.util.mapeamento.DownloadMapping;
import com.vvm.sh.util.metodos.MensagensUtil;

import java.util.ArrayList;
import java.util.List;

public class RecarregarTipoAsyncTask_ extends TipoAsyncTask {

    private OnTransferenciaListener listener;

    public RecarregarTipoAsyncTask_(Context contexto, VvmshBaseDados vvmshBaseDados, CarregamentoTiposRepositorio repositorio, OnTransferenciaListener listener){
        super(vvmshBaseDados, repositorio);
        dialogo = new MensagensUtil(contexto);
        this.listener = listener;
    }

    @Override
    protected void onPreExecute() {
        dialogo.progresso(true, Sintaxe.Frases.ATUALIZAR_TIPO);
    }


//    @Override
//    protected void onPostExecute(List<ITipoListagem> iTipoListagems) {
//        super.onPostExecute(iTipoListagems);
//
//        dialogo.terminarProgresso();
//
//        if(erro != null){
//            dialogo.erro(Sintaxe.Alertas.ERRO_ATUALIZAR_TIPO  + erro);
//        }
//        else{
//            dialogo.sucesso(Sintaxe.Frases.DADOS_ATUALIZADOS_SUCESSO);
//        }
//
//        listener.terminarTransferencia();
//    }



    @Override
    protected void atualizarUI(int index, int limite, Atualizacao atualizacao) {

    }
}
