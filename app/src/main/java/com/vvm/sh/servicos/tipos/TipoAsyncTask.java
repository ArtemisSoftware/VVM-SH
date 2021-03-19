package com.vvm.sh.servicos.tipos;



import com.vvm.sh.api.modelos.pedido.ITipo;
import com.vvm.sh.api.modelos.pedido.ITipoListagem;
import com.vvm.sh.baseDados.VvmshBaseDados;
import com.vvm.sh.baseDados.entidades.Atualizacao;
import com.vvm.sh.baseDados.entidades.Tipo;
import com.vvm.sh.repositorios.CarregamentoTiposRepositorio;
import com.vvm.sh.util.mapeamento.DownloadMapping;

import java.util.ArrayList;
import java.util.List;

public abstract class TipoAsyncTask extends CarregamentoTipoAsyncTask {



    public TipoAsyncTask(VvmshBaseDados vvmshBaseDados, CarregamentoTiposRepositorio repositorio) {
        super(vvmshBaseDados, repositorio);
    }

    @Override
    protected List<Object> filtarRegistos(List<Object> respostas) {

        List<Object> registos = new ArrayList<>();

        for (Object item : respostas) {

            if (item instanceof ITipoListagem) {
                registos.add(item);
            }
        }

        return registos;
    }

    @Override
    protected Atualizacao obterAtualizacao(Object resposta) {
        return DownloadMapping.INSTANCE.map((ITipoListagem)resposta);
    }

    @Override
    protected void inserirRegisto(Object resposta, Atualizacao atualizacao) {

        ITipoListagem registo = (ITipoListagem)resposta;

        List<Tipo> dadosNovos = new ArrayList<>();
        List<Tipo> dadosAlterados = new ArrayList<>();

        for (ITipo item : registo.dadosNovos) {
            dadosNovos.add(DownloadMapping.INSTANCE.map(item, registo));
        }

        for (ITipo item : registo.dadosAlterados) {
            dadosAlterados.add(DownloadMapping.INSTANCE.map(item, registo));
        }

        //--repositorio.atualizarTipo(atualizacao, dadosNovos, dadosAlterados);

    }

}
