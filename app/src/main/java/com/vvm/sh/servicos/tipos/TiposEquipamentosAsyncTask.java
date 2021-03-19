package com.vvm.sh.servicos.tipos;

import android.database.sqlite.SQLiteConstraintException;

import com.vvm.sh.api.modelos.pedido.ICodigoEquipamento;
import com.vvm.sh.baseDados.VvmshBaseDados;
import com.vvm.sh.baseDados.entidades.Atualizacao;
import com.vvm.sh.repositorios.CarregamentoTiposRepositorio;
import com.vvm.sh.util.constantes.Identificadores;

import java.util.ArrayList;
import java.util.List;

public abstract class TiposEquipamentosAsyncTask extends CarregamentoTipoAsyncTask{

    public TiposEquipamentosAsyncTask(VvmshBaseDados vvmshBaseDados, CarregamentoTiposRepositorio repositorio) {
        super(vvmshBaseDados, repositorio);
    }


    @Override
    protected List<Object> filtarRegistos(List<Object> respostas) {

        List<Object> registos = new ArrayList<>();

        for (Object item : respostas) {

            if (item instanceof ICodigoEquipamento) {
                registos.add((ICodigoEquipamento) item);
            }
        }

        return registos;
    }


    @Override
    protected void executar(List<Object> dados) {

        try {

            List<Integer> rejeitados = new ArrayList<>();
            List<Integer> aprovados = new ArrayList<>();

            int index = 0;

            for (Object resposta : dados) {

                ICodigoEquipamento registo = (ICodigoEquipamento) resposta;

                if(registo.rejeitado == Identificadores.Estados.Equipamentos.REJEITADO_PELA_CHEFIA){
                    rejeitados.add(registo.idProvisorioEquipamento);
                }
                if(registo.rejeitado == Identificadores.Estados.Equipamentos.APROVADO_PELA_CHEFIA){
                    aprovados.add(registo.idProvisorioEquipamento);
                }
            }

            repositorio.atualizarEquipamentos(rejeitados, aprovados);

            atualizarUI(++index, dados.size(), null);

            //--listener.atualizarTransferencia(new AtualizacaoUI_(AtualizacaoUI_.Estado.PROCESSAMENTO_EQUIPAMENTOS, 1, 1, "Equipamentos"));
        }
        catch(SQLiteConstraintException throwable){
            erro = throwable.getMessage();
        }
    }

    @Override
    protected Atualizacao obterAtualizacao(Object resposta) {
        return null;
    }

    @Override
    protected void inserirRegisto(Object resposta, Atualizacao atualizacao) {

    }

}
