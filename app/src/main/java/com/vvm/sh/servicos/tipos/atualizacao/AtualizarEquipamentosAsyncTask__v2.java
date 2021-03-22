package com.vvm.sh.servicos.tipos.atualizacao;

import android.database.sqlite.SQLiteConstraintException;

import com.vvm.sh.api.modelos.pedido.ICodigoEquipamento;
import com.vvm.sh.baseDados.VvmshBaseDados;
import com.vvm.sh.baseDados.entidades.Atualizacao;
import com.vvm.sh.repositorios.CarregamentoTiposRepositorio;
import com.vvm.sh.servicos.tipos.TiposEquipamentosAsyncTask;
import com.vvm.sh.ui.transferencias.adaptadores.OnTransferenciaListener;
import com.vvm.sh.util.AtualizacaoUI_;
import com.vvm.sh.util.constantes.Identificadores;

import java.util.ArrayList;
import java.util.List;

public class AtualizarEquipamentosAsyncTask__v2 extends TiposEquipamentosAsyncTask {

    private OnTransferenciaListener listener;

    public AtualizarEquipamentosAsyncTask__v2(OnTransferenciaListener listener, VvmshBaseDados vvmshBaseDados, CarregamentoTiposRepositorio repositorio){
        super(vvmshBaseDados, repositorio);

        this.listener = listener;
    }

    @Override
    protected void onPostExecute(List<Object> iTipoListagems) {

        listener.atualizarTransferencia(new AtualizacaoUI_(AtualizacaoUI_.Estado.PROCESSAMENTO_EQUIPAMENTOS, erro));

        listener.terminarTransferencia();
    }


    @Override
    protected void atualizarUI(int index, int limite, Atualizacao atualizacao) {
        listener.atualizarTransferencia(new AtualizacaoUI_(AtualizacaoUI_.Estado.PROCESSAMENTO_EQUIPAMENTOS, index, limite, "Equipamentos"));
    }

//
//
//    @Override
//    protected List<Object>[] doInBackground(List<Object>... tipoRespostas) {
//
//        if(tipoRespostas[0] == null)
//            return null;
//
//        List<Object> respostas = tipoRespostas[0];
//        List<ICodigoEquipamento> dados = new ArrayList<>();
//
//        for (Object item : respostas) {
//
//            if (item instanceof ICodigoEquipamento) {
//                dados.add((ICodigoEquipamento) item);
//            }
//        }
//
//
//        this.vvmshBaseDados.runInTransaction(new Runnable(){
//            @Override
//            public void run(){
//
//                try {
//
//                    List<Integer> rejeitados = new ArrayList<>();
//                    List<Integer> aprovados = new ArrayList<>();
//
//                    for (ICodigoEquipamento resposta : dados) {
//
//                        if(resposta.rejeitado == Identificadores.Estados.Equipamentos.REJEITADO_PELA_CHEFIA){
//                            rejeitados.add(resposta.idProvisorioEquipamento);
//                        }
//                        if(resposta.rejeitado == Identificadores.Estados.Equipamentos.APROVADO_PELA_CHEFIA){
//                            aprovados.add(resposta.idProvisorioEquipamento);
//                        }
//                    }
//
//                    repositorio.atualizarEquipamentos(rejeitados, aprovados);
//
//                    listener.atualizarTransferencia(new AtualizacaoUI_(AtualizacaoUI_.Estado.PROCESSAMENTO_EQUIPAMENTOS, 1, 1, "Equipamentos"));
//                }
//                catch(SQLiteConstraintException throwable){
//                    erro = throwable.getMessage();
//                }
//            }
//        });
//
//        return null;
//    }
//
//    @Override
//    protected void onPostExecute(List<Object>[] objects) {
//
//        listener.atualizarTransferencia(new AtualizacaoUI_(AtualizacaoUI_.Estado.PROCESSAMENTO_EQUIPAMENTOS, erro));
//
//        listener.terminarTransferencia();
//    }

}
