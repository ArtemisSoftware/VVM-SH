package com.vvm.sh.servicos;

import android.database.sqlite.SQLiteConstraintException;
import android.os.AsyncTask;
import android.os.Handler;

import com.vvm.sh.api.modelos.TipoResposta;
import com.vvm.sh.api.modelos.TipoResultado;
import com.vvm.sh.baseDados.VvmshBaseDados;
import com.vvm.sh.baseDados.entidades.Atualizacao;
import com.vvm.sh.baseDados.entidades.Tipo;
import com.vvm.sh.repositorios.TiposRepositorio;
import com.vvm.sh.util.AtualizacaoUI;
import com.vvm.sh.util.mapeamento.ModelMapping;

import java.util.ArrayList;
import java.util.List;

public class CarregarTipoAsyncTask extends AsyncTask<List<TipoResposta>, Void, Void> {

    private String errorMessage;
    private VvmshBaseDados vvmshBaseDados;
    private TiposRepositorio repositorio;


    /**
     * Permite enviar mensagens para fora do servico
     */
    private AtualizacaoUI atualizacaoUI;

    public CarregarTipoAsyncTask(VvmshBaseDados vvmshBaseDados, Handler handler, TiposRepositorio repositorio){
        this.vvmshBaseDados = vvmshBaseDados;
        this.repositorio = repositorio;
        atualizacaoUI = new AtualizacaoUI(handler);
    }


    @Override
    protected Void doInBackground(List<TipoResposta>... tipoRespostas) {

        if(tipoRespostas[0] == null)
            return null;

        List<TipoResposta> respostas = tipoRespostas[0];

        this.vvmshBaseDados.runInTransaction(new Runnable(){
            @Override
            public void run(){

                try {


                    int index = 0;

                    for(TipoResposta resposta : respostas){

                        List<Tipo> dadosNovos = new ArrayList<>();
                        List<Tipo> dadosAlterados = new ArrayList<>();

                        Atualizacao atualizacao = ModelMapping.INSTANCE.map(resposta);

                        for (TipoResultado item : resposta.dadosNovos) {
                            dadosNovos.add(ModelMapping.INSTANCE.map(item, resposta));
                        }

                        for (TipoResultado item : resposta.dadosAlterados) {
                            dadosAlterados.add(ModelMapping.INSTANCE.map(item, resposta));
                        }

                        repositorio.carregarTipo(atualizacao, dadosNovos, dadosAlterados);

                        atualizacaoUI.atualizarUI(AtualizacaoUI.Codigo.PROCESSAMENTO_DADOS, atualizacao.descricao, ++index, respostas.size());
                    }

                    atualizacaoUI.atualizarUI(AtualizacaoUI.Codigo.PROCESSAMENTO_TIPOS_CONCLUIDO, "Concluido", index, respostas.size());

                }
                catch(SQLiteConstraintException throwable){
                    errorMessage = throwable.getMessage();
                }
            }
        });

        return null;
    }
}
