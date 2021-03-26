package com.vvm.sh.servicos.tipos;

import android.database.sqlite.SQLiteConstraintException;
import android.os.AsyncTask;

import com.vvm.sh.api.modelos.pedido.ITipoListagem;
import com.vvm.sh.baseDados.VvmshBaseDados;
import com.vvm.sh.baseDados.entidades.Atualizacao;
import com.vvm.sh.baseDados.entidades.MedidaAveriguacao;
import com.vvm.sh.repositorios.CarregamentoTiposRepositorio;
import com.vvm.sh.util.constantes.Identificadores;
import com.vvm.sh.util.mapeamento.DownloadMapping;
import com.vvm.sh.util.metodos.MensagensUtil;

import java.util.List;

public abstract class CarregamentoTipoAsyncTask extends AsyncTask<List<Object>, Void, List<Object>> {

    protected String erro;
    protected VvmshBaseDados vvmshBaseDados;
    protected CarregamentoTiposRepositorio repositorio;
    protected MensagensUtil dialogo;

    public CarregamentoTipoAsyncTask(VvmshBaseDados vvmshBaseDados, CarregamentoTiposRepositorio repositorio) {
        this.vvmshBaseDados = vvmshBaseDados;
        this.repositorio = repositorio;
        this.erro = null;
    }

    @Override
    protected List<Object> doInBackground(List<Object>... respostasApi) {

        if(respostasApi[0] == null)
            return null;

        List<Object> respostas = filtarRegistos(respostasApi[0]);

        this.vvmshBaseDados.runInTransaction(new Runnable(){
            @Override
            public void run(){

                executar(respostas);
            }
        });

        return respostasApi[0];
    }


    protected void executar(List<Object> dados){

        try {

            int index = 0;

            for (Object resposta : dados) {

                Atualizacao atualizacao = obterAtualizacao(resposta);

                inserirRegisto(resposta, atualizacao);

                atualizarUI(++index, dados.size(), atualizacao);

            }
        }
        catch(SQLiteConstraintException throwable){
            erro = throwable.getMessage();
        }
    }



    protected String obterDescricaoApi(Atualizacao atualizacao){

        String resultado = "";

        if(atualizacao == null){
            return resultado;
        }

        switch (atualizacao.api){

            case Identificadores.App.APP_SA:

                resultado = Identificadores.App.SA;
                break;

            case Identificadores.App.APP_ST:

                resultado = Identificadores.App.ST;
                break;

            default:
                break;

        }

        return resultado;

    }


    //-----------------------
    //Metodos abstratos
    //-----------------------

    protected abstract List<Object> filtarRegistos(List<Object> respostas);

    protected abstract Atualizacao obterAtualizacao(Object resposta);

    protected abstract void inserirRegisto(Object resposta, Atualizacao atualizacao);

    protected abstract void atualizarUI(int index, int limite, Atualizacao atualizacao);
}
