package com.vvm.sh.servicos.tipos;

import android.database.sqlite.SQLiteConstraintException;
import android.os.AsyncTask;

import com.vvm.sh.api.modelos.pedido.ITipoListagem;
import com.vvm.sh.baseDados.VvmshBaseDados;
import com.vvm.sh.baseDados.entidades.Atualizacao;
import com.vvm.sh.repositorios.CarregamentoTiposRepositorio;
import com.vvm.sh.util.constantes.Identificadores;
import com.vvm.sh.util.mapeamento.DownloadMapping;
import com.vvm.sh.util.metodos.MensagensUtil;

import java.util.List;

public abstract class CarregamentoTipoAsyncTask extends AsyncTask<List<ITipoListagem>, Void, List<ITipoListagem>> {

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
    protected List<ITipoListagem> doInBackground(List<ITipoListagem>... respostasApi) {

        if(respostasApi[0] == null)
            return null;

        List<ITipoListagem> respostas = respostasApi[0];

        this.vvmshBaseDados.runInTransaction(new Runnable(){
            @Override
            public void run(){

                executar(respostas);
            }
        });

        return null;
    }

    private void executar(List<ITipoListagem> dados){

        try {

            int index = 0;

            for (ITipoListagem resposta : dados) {

                Atualizacao atualizacao = DownloadMapping.INSTANCE.map(resposta);

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

    protected abstract void inserirRegisto(ITipoListagem resposta, Atualizacao atualizacao);

    protected abstract void atualizarUI(int index, int limite, Atualizacao atualizacao);
}
