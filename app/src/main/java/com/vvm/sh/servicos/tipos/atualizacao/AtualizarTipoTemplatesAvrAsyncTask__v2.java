package com.vvm.sh.servicos.tipos.atualizacao;

import android.database.sqlite.SQLiteConstraintException;

import com.vvm.sh.api.modelos.pedido.ITipoTemplateAvrLevantamento;
import com.vvm.sh.api.modelos.pedido.ITipoTemplateAvrLevantamentoListagem;
import com.vvm.sh.api.modelos.pedido.ITipoTemplateAvrRisco;
import com.vvm.sh.api.modelos.pedido.ITipoTemplateAvrRiscoListagem;
import com.vvm.sh.baseDados.VvmshBaseDados;
import com.vvm.sh.baseDados.entidades.Atualizacao;
import com.vvm.sh.baseDados.entidades.TipoTemplateAvrLevantamento;
import com.vvm.sh.baseDados.entidades.TipoTemplateAvrRisco;
import com.vvm.sh.baseDados.entidades.TipoTemplatesAVRMedidaRisco;
import com.vvm.sh.repositorios.CarregamentoTiposRepositorio;
import com.vvm.sh.servicos.tipos.TipoTemplatesAvrAsyncTask;
import com.vvm.sh.ui.transferencias.adaptadores.OnTransferenciaListener;
import com.vvm.sh.util.AtualizacaoUI_;
import com.vvm.sh.util.constantes.Identificadores;
import com.vvm.sh.util.mapeamento.DownloadMapping;

import java.util.ArrayList;
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

        AtualizarEquipamentosAsyncTask_ servico = new AtualizarEquipamentosAsyncTask_(listener, vvmshBaseDados, repositorio);
        servico.execute(iTipoListagems);

    }

    @Override
    protected void atualizarUI(int index, int limite, Atualizacao atualizacao) {
        listener.atualizarTransferencia(new AtualizacaoUI_(AtualizacaoUI_.Estado.PROCESSAMENTO_TEMPLATE_AVALIACAO_RISCOS, index, limite, obterDescricaoApi(atualizacao) + " " + atualizacao.descricao));

    }
}
