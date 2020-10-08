package com.vvm.sh.servicos;

import android.database.sqlite.SQLiteConstraintException;
import android.os.AsyncTask;
import android.os.Handler;

import com.vvm.sh.api.modelos.pedido.ITipoListagem;
import com.vvm.sh.api.modelos.pedido.ITipo;
import com.vvm.sh.baseDados.VvmshBaseDados;
import com.vvm.sh.repositorios.TiposRepositorio;
import com.vvm.sh.baseDados.entidades.Atualizacao;
import com.vvm.sh.baseDados.entidades.Tipo;
import com.vvm.sh.util.AtualizacaoUI;
import com.vvm.sh.util.excepcoes.TipoInexistenteException;
import com.vvm.sh.util.mapeamento.DownloadMapping;
import com.vvm.sh.util.metodos.TiposUtil;

import java.util.ArrayList;
import java.util.List;

public class AtualizarTipoAsyncTask extends AsyncTask<List<ITipoListagem>, Void, Void> {

    private String errorMessage;
    private VvmshBaseDados vvmshBaseDados;
    private TiposRepositorio repositorio;
    private AtualizacaoUI atualizacaoUI;

    public AtualizarTipoAsyncTask(VvmshBaseDados vvmshBaseDados, Handler handlerUI, TiposRepositorio repositorio){
        this.vvmshBaseDados = vvmshBaseDados;
        this.repositorio = repositorio;
        this.atualizacaoUI = new AtualizacaoUI(handlerUI);
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

                    try {

                        String registoTipo = "";

                        for (TiposUtil.MetodoApi metodo : TiposUtil.obterMetodos()){

                            Atualizacao atualizacao = null;
                            List<ITipoListagem> registos = new ArrayList<>();

                            for (ITipoListagem tipo : respostas) {

                                if(tipo.metodo.equals(metodo.descricao) == true){

                                    atualizacao = DownloadMapping.INSTANCE.map(tipo);
                                    registos.add(tipo);
                                }
                            }


                            if(atualizacao != null) {

                                registoTipo = atualizacao.descricao;

                                List<Tipo> tipos = new ArrayList<>();

                                for (ITipoListagem resposta : registos) {
                                    tipos.addAll(obterTipos(resposta));
                                }

                                repositorio.atualizarTipo(atualizacao, tipos);
                            }
                        }

                        atualizacaoUI.atualizarUI(AtualizacaoUI.Codigo.CONCLUIR_ATUALIZACAO_TIPO, "Tipo " + registoTipo + " atualizado com sucesso");
                    }
                    catch (TipoInexistenteException throwable) {
                        errorMessage = throwable.getMessage();
                    }


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
    private List<Tipo> obterTipos(ITipoListagem resposta) {

        List<Tipo> tipos = new ArrayList<>();

        for (ITipo item : resposta.dadosNovos) {

            Tipo resultado = DownloadMapping.INSTANCE.map(item, resposta);
            tipos.add(resultado);
        }

        return tipos;
    }
}
