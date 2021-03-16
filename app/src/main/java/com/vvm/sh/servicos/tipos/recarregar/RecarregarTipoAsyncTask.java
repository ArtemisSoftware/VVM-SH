package com.vvm.sh.servicos.tipos.recarregar;

import android.content.Context;
import android.database.sqlite.SQLiteConstraintException;
import android.os.AsyncTask;
import android.os.Handler;

import com.vvm.sh.api.modelos.pedido.ITipoListagem;
import com.vvm.sh.api.modelos.pedido.ITipo;
import com.vvm.sh.baseDados.VvmshBaseDados;
import com.vvm.sh.repositorios.CarregamentoTiposRepositorio;
import com.vvm.sh.baseDados.entidades.Atualizacao;
import com.vvm.sh.baseDados.entidades.Tipo;
import com.vvm.sh.util.AtualizacaoUI;
import com.vvm.sh.util.constantes.Sintaxe;
import com.vvm.sh.util.mapeamento.DownloadMapping;
import com.vvm.sh.util.metodos.MensagensUtil;

import java.util.ArrayList;
import java.util.List;

public class RecarregarTipoAsyncTask extends AsyncTask<List<ITipoListagem>, Void, Void> {

    private String erro;
    private VvmshBaseDados vvmshBaseDados;
    private CarregamentoTiposRepositorio repositorio;

    private MensagensUtil dialogo;

    public RecarregarTipoAsyncTask(Context contexto, VvmshBaseDados vvmshBaseDados, CarregamentoTiposRepositorio repositorio){
        this.vvmshBaseDados = vvmshBaseDados;
        this.repositorio = repositorio;
        dialogo = new MensagensUtil(contexto);
    }

    @Override
    protected void onPreExecute() {
        dialogo.progresso(true, Sintaxe.Frases.ATUALIZAR_TIPO);
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

                    for(ITipoListagem resposta : respostas){

                        Atualizacao atualizacao = DownloadMapping.INSTANCE.map(resposta);
                        List<Tipo> dadosNovos = new ArrayList<>();
                        List<Tipo> dadosAlterados = new ArrayList<>();

                        for (ITipo item : resposta.dadosNovos) {
                            dadosNovos.add(DownloadMapping.INSTANCE.map(item, resposta));
                        }

                        for (ITipo item : resposta.dadosAlterados) {
                            dadosAlterados.add(DownloadMapping.INSTANCE.map(item, resposta));
                        }

                        repositorio.recarregarTipo(atualizacao, dadosNovos, dadosAlterados);
                    }
                }
                catch(SQLiteConstraintException throwable){
                    erro = throwable.getMessage();
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


    @Override
    protected void onPostExecute(Void result) {
        super.onPostExecute(result);

        dialogo.terminarProgresso();

        if(erro != null){
            dialogo.erro(Sintaxe.Alertas.ERRO_ATUALIZAR_TIPO  + erro);
        }
        else{
            dialogo.sucesso(Sintaxe.Frases.DADOS_ATUALIZADOS_SUCESSO);
        }
    }
}
