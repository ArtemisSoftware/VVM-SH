package com.vvm.sh.servicos.tipos.atualizacao;

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
import com.vvm.sh.util.AtualizacaoUI_;
import com.vvm.sh.util.constantes.AppConfig;
import com.vvm.sh.util.constantes.Identificadores;
import com.vvm.sh.util.mapeamento.DownloadMapping;

import java.util.ArrayList;
import java.util.List;

public class AtualizarTipoAsyncTask__v2  extends TipoAsyncTask {


    private OnTransferenciaListener listener;

    public AtualizarTipoAsyncTask__v2(OnTransferenciaListener listener, VvmshBaseDados vvmshBaseDados, CarregamentoTiposRepositorio repositorio){
        super(vvmshBaseDados, repositorio);
        this.listener = listener;
    }



    @Override
    protected void onPostExecute(List<Object> objects) {

        listener.atualizarTransferencia(new AtualizacaoUI_(AtualizacaoUI_.Estado.PROCESSAMENTO_TIPOS, erro));


        if(AppConfig.APP_MODO == AppConfig.APP_SA){
            listener.terminarTransferencia();
        }
        else{//listener.terminarTransferencia();
            AtualizarTipoAtividadesPlaneaveisAsyncTask__v2 servico = new AtualizarTipoAtividadesPlaneaveisAsyncTask__v2(listener, vvmshBaseDados, repositorio);
            servico.execute(objects);
        }




//        if(AppConfig.APP_MODO == AppConfig.APP_SA){
//            atualizacaoUI.atualizarUI(AtualizacaoUI.Codigo.PROCESSAMENTO_TIPOS_CONCLUIDO, "Concluido", 2, 2);
//        }
//        else {
//            AtualizarTipoAtividadesPlaneaveisAsyncTask servico = new AtualizarTipoAtividadesPlaneaveisAsyncTask(vvmshBaseDados, handlerUI, repositorio);
//            servico.execute(objects);
//        }
    }



    @Override
    protected void atualizarUI(int index, int limite, Atualizacao atualizacao) {
        listener.atualizarTransferencia(new AtualizacaoUI_(AtualizacaoUI_.Estado.PROCESSAMENTO_TIPOS, index, limite, obterDescricaoApi(atualizacao) + " " + atualizacao.descricao));

    }
}
