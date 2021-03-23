package com.vvm.sh.servicos.tipos.atualizacao;

import com.vvm.sh.baseDados.VvmshBaseDados;
import com.vvm.sh.baseDados.entidades.Atualizacao;
import com.vvm.sh.repositorios.CarregamentoTiposRepositorio;
import com.vvm.sh.servicos.tipos.TipoAtividadesPlaneaveisAsyncTask;
import com.vvm.sh.ui.transferencias.adaptadores.OnTransferenciaListener;
import com.vvm.sh.util.AtualizacaoUI_;

import java.util.List;

public class AtualizarTipoChecklistAsyncTask__v2 extends TipoAtividadesPlaneaveisAsyncTask {

    private OnTransferenciaListener listener;

    public AtualizarTipoChecklistAsyncTask__v2(OnTransferenciaListener listener, VvmshBaseDados vvmshBaseDados, CarregamentoTiposRepositorio repositorio){
        super(vvmshBaseDados, repositorio);

        this.listener = listener;
    }



    @Override
    protected void onPostExecute(List<Object> iTipoListagems) {

        listener.atualizarTransferencia(new AtualizacaoUI_(AtualizacaoUI_.Estado.PROCESSAMENTO_CHECKLIST, erro));

        AtualizarEquipamentosAsyncTask__v2 servico = new AtualizarEquipamentosAsyncTask__v2(listener, vvmshBaseDados, repositorio);
        servico.execute(iTipoListagems);
    }

    @Override
    protected void atualizarUI(int index, int limite, Atualizacao atualizacao) {
        listener.atualizarTransferencia(new AtualizacaoUI_(AtualizacaoUI_.Estado.PROCESSAMENTO_CHECKLIST, index, limite, obterDescricaoApi(atualizacao) + " Checklist"));

    }
}
