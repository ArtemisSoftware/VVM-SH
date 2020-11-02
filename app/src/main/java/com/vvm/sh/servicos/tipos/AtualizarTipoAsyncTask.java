package com.vvm.sh.servicos.tipos;

import android.content.Context;
import android.database.sqlite.SQLiteConstraintException;
import android.os.AsyncTask;
import android.os.Handler;

import com.vvm.sh.api.modelos.pedido.ITipo;
import com.vvm.sh.api.modelos.pedido.ITipoListagem;
import com.vvm.sh.baseDados.VvmshBaseDados;
import com.vvm.sh.baseDados.entidades.Atualizacao;
import com.vvm.sh.baseDados.entidades.Tipo;
import com.vvm.sh.repositorios.CarregamentoTiposRepositorio;
import com.vvm.sh.util.AtualizacaoUI;
import com.vvm.sh.util.constantes.Sintaxe;
import com.vvm.sh.util.mapeamento.DownloadMapping;
import com.vvm.sh.util.metodos.MensagensUtil;

import java.util.ArrayList;
import java.util.List;

public class AtualizarTipoAsyncTask extends AsyncTask<List<ITipoListagem>, Void, Void> {

    private String errorMessage;
    private VvmshBaseDados vvmshBaseDados;
    private CarregamentoTiposRepositorio repositorio;


    /**
     * Permite enviar mensagens para fora do servico
     */
    private AtualizacaoUI atualizacaoUI;

    public AtualizarTipoAsyncTask(Context contexto, VvmshBaseDados vvmshBaseDados, Handler handlerUI, CarregamentoTiposRepositorio repositorio){
        this.vvmshBaseDados = vvmshBaseDados;
        this.repositorio = repositorio;
        atualizacaoUI = new AtualizacaoUI(handlerUI);
    }


    @Override
    protected Void doInBackground(List<ITipoListagem>... tipoRespostas) {

        if(tipoRespostas[0] == null)
            return null;

        List<ITipoListagem> respostas = tipoRespostas[0];

        this.vvmshBaseDados.runInTransaction(new Runnable(){
            @Override
            public void run(){

                try {

                    int index = 0;

                    for (ITipoListagem resposta : respostas) {

                        Atualizacao atualizacao = DownloadMapping.INSTANCE.map(resposta);

                        List<Tipo> dadosNovos = obterTipos(resposta, resposta.dadosNovos);
                        List<Tipo> dadosAlterados = obterTipos(resposta, resposta.dadosAlterados);

                        repositorio.atualizarTipo(atualizacao, dadosNovos, dadosAlterados);

                        atualizacaoUI.atualizarUI(AtualizacaoUI.Codigo.PROCESSAMENTO_TIPOS, atualizacao.descricao, ++index, respostas.size());
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


    /**
     * Metodo que permite obter os tipos
     * @param resposta os dados dos tipos
     * @return uma lista de tipos
     */
    private List<Tipo> obterTipos(ITipoListagem resposta, List<ITipo> dados) {

        List<Tipo> tipos = new ArrayList<>();

        for (ITipo item : dados) {

            Tipo resultado = DownloadMapping.INSTANCE.map(item, resposta);
            tipos.add(resultado);
        }

        return tipos;
    }


}
