package com.vvm.sh.servicos.tipos.atualizacao;

import android.content.Context;
import android.database.sqlite.SQLiteConstraintException;
import android.os.AsyncTask;
import android.os.Handler;

import com.vvm.sh.api.modelos.pedido.Codigo;
import com.vvm.sh.api.modelos.pedido.ICodigoEquipamento;
import com.vvm.sh.api.modelos.pedido.ITipo;
import com.vvm.sh.api.modelos.pedido.ITipoListagem;
import com.vvm.sh.baseDados.VvmshBaseDados;
import com.vvm.sh.baseDados.entidades.Atualizacao;
import com.vvm.sh.baseDados.entidades.Tipo;
import com.vvm.sh.repositorios.CarregamentoTiposRepositorio;
import com.vvm.sh.util.AtualizacaoUI;
import com.vvm.sh.util.constantes.Identificadores;
import com.vvm.sh.util.mapeamento.DownloadMapping;

import java.util.ArrayList;
import java.util.List;

public class AtualizarEquipamentosAsyncTask extends AsyncTask<List<Object>, Void, Void> {

    private String errorMessage;
    private Handler handlerUI;
    private VvmshBaseDados vvmshBaseDados;
    private CarregamentoTiposRepositorio repositorio;



    /**
     * Permite enviar mensagens para fora do servico
     */
    private AtualizacaoUI atualizacaoUI;

    public AtualizarEquipamentosAsyncTask(VvmshBaseDados vvmshBaseDados, Handler handlerUI, CarregamentoTiposRepositorio repositorio){
        this.vvmshBaseDados = vvmshBaseDados;
        this.repositorio = repositorio;
        atualizacaoUI = new AtualizacaoUI(handlerUI);

        this.handlerUI = handlerUI;
    }



    @Override
    protected Void doInBackground(List<Object>... tipoRespostas) {

        if(tipoRespostas[0] == null)
            return null;

        List<Object> respostas = tipoRespostas[0];
        List<ICodigoEquipamento> dados = new ArrayList<>();

        for (Object item : respostas) {

            if (item instanceof ICodigoEquipamento) {
                dados.add((ICodigoEquipamento) item);
            }
        }


        this.vvmshBaseDados.runInTransaction(new Runnable(){
            @Override
            public void run(){

                try {

                    List<Integer> rejeitados = new ArrayList<>();
                    List<Integer> aprovados = new ArrayList<>();

                    for (ICodigoEquipamento resposta : dados) {

                        if(resposta.rejeitado == Identificadores.Estados.Equipamentos.REJEITADO_PELA_CHEFIA){
                            rejeitados.add(resposta.idProvisorioEquipamento);
                        }
                        if(resposta.rejeitado == Identificadores.Estados.Equipamentos.APROVADO_PELA_CHEFIA){
                            aprovados.add(resposta.idProvisorioEquipamento);
                        }
                    }

                    repositorio.atualizarEquipamentos(rejeitados, aprovados);

                    atualizacaoUI.atualizarUI(AtualizacaoUI.Codigo.PROCESSAMENTO_TIPOS, "Equipamentos", 1, 2);
                }
                catch(SQLiteConstraintException throwable){
                    errorMessage = throwable.getMessage();
                }
            }
        });

        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        atualizacaoUI.atualizarUI(AtualizacaoUI.Codigo.PROCESSAMENTO_TIPOS_CONCLUIDO, "Concluido", 2, 2);
    }

}
