package com.vvm.sh.servicos.tipos.atualizacao;

import android.database.sqlite.SQLiteConstraintException;

import com.vvm.sh.api.modelos.pedido.ITipoAtividadePlaneavel;
import com.vvm.sh.api.modelos.pedido.ITipoAtividadePlaneavelListagem;
import com.vvm.sh.api.modelos.pedido.ITipoListagem;
import com.vvm.sh.baseDados.VvmshBaseDados;
import com.vvm.sh.baseDados.entidades.Atualizacao;
import com.vvm.sh.baseDados.entidades.TipoAtividadePlaneavel;
import com.vvm.sh.repositorios.CarregamentoTiposRepositorio;
import com.vvm.sh.servicos.tipos.TipoAtividadesPlaneaveisAsyncTask;
import com.vvm.sh.ui.transferencias.adaptadores.OnTransferenciaListener;
import com.vvm.sh.util.AtualizacaoUI_;
import com.vvm.sh.util.constantes.Identificadores;
import com.vvm.sh.util.mapeamento.DownloadMapping;

import java.util.ArrayList;
import java.util.List;

public class AtualizarTipoAtividadesPlaneaveisAsyncTask__v2 extends TipoAtividadesPlaneaveisAsyncTask {

    private OnTransferenciaListener listener;

    public AtualizarTipoAtividadesPlaneaveisAsyncTask__v2(OnTransferenciaListener listener, VvmshBaseDados vvmshBaseDados, CarregamentoTiposRepositorio repositorio){
        super(vvmshBaseDados, repositorio);

        this.listener = listener;
    }



    @Override
    protected void onPostExecute(List<ITipoListagem> iTipoListagems) {

        listener.atualizarTransferencia(new AtualizacaoUI_(AtualizacaoUI_.Estado.PROCESSAMENTO_ATIVIDADES_PLANEAVEIS, erro));


//        AtualizarTipoTemplatesAvrAsyncTask_ servico = new AtualizarTipoTemplatesAvrAsyncTask_(listener, vvmshBaseDados, repositorio);
//        servico.execute(objects);
    }

    @Override
    protected void atualizarUI(int index, int limite, Atualizacao atualizacao) {
        listener.atualizarTransferencia(new AtualizacaoUI_(AtualizacaoUI_.Estado.PROCESSAMENTO_ATIVIDADES_PLANEAVEIS, ++index, limite, obterDescricaoApi(atualizacao) + " " + atualizacao.descricao));

    }
}
