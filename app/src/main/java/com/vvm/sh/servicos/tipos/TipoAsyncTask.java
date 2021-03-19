package com.vvm.sh.servicos.tipos;

import android.database.sqlite.SQLiteConstraintException;
import android.os.AsyncTask;

import com.vvm.sh.api.modelos.pedido.ITipo;
import com.vvm.sh.api.modelos.pedido.ITipoListagem;
import com.vvm.sh.baseDados.VvmshBaseDados;
import com.vvm.sh.baseDados.entidades.Atualizacao;
import com.vvm.sh.baseDados.entidades.Tipo;
import com.vvm.sh.repositorios.CarregamentoTiposRepositorio;
import com.vvm.sh.util.constantes.Identificadores;
import com.vvm.sh.util.mapeamento.DownloadMapping;

import java.util.ArrayList;
import java.util.List;

public abstract class TipoAsyncTask extends CarregamentoTipoAsyncTask {



    public TipoAsyncTask(VvmshBaseDados vvmshBaseDados, CarregamentoTiposRepositorio repositorio) {
        super(vvmshBaseDados, repositorio);
    }


    @Override
    protected void inserirRegisto(ITipoListagem resposta, Atualizacao atualizacao){

        if(atualizacao.tipo == Identificadores.Atualizacoes.TIPO) {

            List<Tipo> dadosNovos = new ArrayList<>();
            List<Tipo> dadosAlterados = new ArrayList<>();

            for (ITipo item : resposta.dadosNovos) {
                dadosNovos.add(DownloadMapping.INSTANCE.map(item, resposta));
            }

            for (ITipo item : resposta.dadosAlterados) {
                dadosAlterados.add(DownloadMapping.INSTANCE.map(item, resposta));
            }

            repositorio.atualizarTipo(atualizacao, dadosNovos, dadosAlterados);
        }
    }


}
