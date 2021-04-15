package com.vvm.sh.servicos.tipos;

import com.vvm.sh.api.modelos.pedido.ITipoAtividadePlaneavel;
import com.vvm.sh.api.modelos.pedido.ITipoAtividadePlaneavelListagem;
import com.vvm.sh.baseDados.VvmshBaseDados;
import com.vvm.sh.baseDados.entidades.Atualizacao;
import com.vvm.sh.baseDados.entidades.TipoAtividadePlaneavel;
import com.vvm.sh.baseDados.entidades.TipoTemplateAvrLevantamento;
import com.vvm.sh.baseDados.entidades.TipoTemplateAvrRisco;
import com.vvm.sh.baseDados.entidades.TipoTemplatesAVRMedidaRisco;
import com.vvm.sh.repositorios.CarregamentoTiposRepositorio;
import com.vvm.sh.util.mapeamento.DownloadMapping;

import java.util.ArrayList;
import java.util.List;

public abstract class TipoAtividadesPlaneaveisAsyncTask extends CarregamentoTipoAsyncTask{


    public TipoAtividadesPlaneaveisAsyncTask(VvmshBaseDados vvmshBaseDados, CarregamentoTiposRepositorio repositorio) {
        super(vvmshBaseDados, repositorio);
    }

    @Override
    protected List<Object> filtarRegistos(List<Object> respostas) {

        List<Object> registos = new ArrayList<>();

        for (Object item : respostas) {

            if (item instanceof ITipoAtividadePlaneavelListagem) {
                registos.add((ITipoAtividadePlaneavelListagem) item);
            }
        }

        return registos;
    }

    @Override
    protected Atualizacao obterAtualizacao(Object resposta) {
        return DownloadMapping.INSTANCE.map((ITipoAtividadePlaneavelListagem)resposta);
    }

    @Override
    protected void inserirRegisto(Object resposta, Atualizacao atualizacao) {

        ITipoAtividadePlaneavelListagem registo = (ITipoAtividadePlaneavelListagem)resposta;

        List<TipoAtividadePlaneavel> dadosNovos = new ArrayList<>();
        List<TipoAtividadePlaneavel> dadosAlterados = new ArrayList<>();

        for (ITipoAtividadePlaneavel item : registo.dadosNovos) {
            dadosNovos.add(DownloadMapping.INSTANCE.map((ITipoAtividadePlaneavel)item, registo));
        }

        for (ITipoAtividadePlaneavel item : registo.dadosAlterados) {
            dadosAlterados.add(DownloadMapping.INSTANCE.map(item, registo));
        }

        inserirRegistoBd(atualizacao, dadosNovos, dadosAlterados);
    }



    //-----------------------
    //Metodos abstratos
    //-----------------------


    protected abstract void inserirRegistoBd(Atualizacao atualizacao, List<TipoAtividadePlaneavel> dadosNovos, List<TipoAtividadePlaneavel> dadosAlterados);



}
