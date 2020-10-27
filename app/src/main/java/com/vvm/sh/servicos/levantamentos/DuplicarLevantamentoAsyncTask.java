package com.vvm.sh.servicos.levantamentos;

import android.database.sqlite.SQLiteConstraintException;
import android.os.AsyncTask;
import android.os.Handler;

import com.vvm.sh.api.modelos.pedido.ITipoListagem;
import com.vvm.sh.baseDados.VvmshBaseDados;
import com.vvm.sh.baseDados.entidades.LevantamentoRiscoResultado;
import com.vvm.sh.baseDados.entidades.MedidaResultado;
import com.vvm.sh.baseDados.entidades.RiscoResultado;
import com.vvm.sh.repositorios.LevantamentoAvaliacaoRepositorio;
import com.vvm.sh.repositorios.TiposRepositorio;
import com.vvm.sh.util.AtualizacaoUI;
import com.vvm.sh.util.constantes.Identificadores;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

public class DuplicarLevantamentoAsyncTask  extends AsyncTask<Integer, Void, Void> {


    private String errorMessage;
    private LevantamentoRiscoResultado levantamento;
    private VvmshBaseDados vvmshBaseDados;
    private LevantamentoAvaliacaoRepositorio repositorio;

    public DuplicarLevantamentoAsyncTask(VvmshBaseDados vvmshBaseDados, LevantamentoAvaliacaoRepositorio repositorio, LevantamentoRiscoResultado levantamento){
        this.vvmshBaseDados = vvmshBaseDados;
        this.repositorio = repositorio;
        this.levantamento = levantamento;
    }


    @Override
    protected Void doInBackground(Integer... integers) {

        if(integers[0] == null)
            return null;

        int idLevantamentoNovo = integers[0];

        this.vvmshBaseDados.runInTransaction(new Runnable(){
            @Override
            public void run(){
                try {

                    repositorio.duplicarCategoriasProfissionais(levantamento.id, idLevantamentoNovo);
                    repositorio.duplicarRiscos(levantamento.id, idLevantamentoNovo);

                    duplicarMedidas(idLevantamentoNovo, Identificadores.Origens.LEVANTAMENTO_MEDIDAS_RECOMENDADAS);
                    duplicarMedidas(idLevantamentoNovo, Identificadores.Origens.LEVANTAMENTO_MEDIDAS_ADOPTADAS);

                    repositorio.duplicarPlanoAcao(levantamento.idAtividade, idLevantamentoNovo, Identificadores.Origens.LEVANTAMENTO_MEDIDAS_RECOMENDADAS);
                }
                catch(SQLiteConstraintException throwable){
                    errorMessage = throwable.getMessage();
                }
            }
        });

        return null;
    }


    /**
     * Metodo que permite duplicar medidas
     * @param idLevantamentoNovo o identificador do novo levantamento
     * @param origem a origem das medidas
     */
    private void duplicarMedidas(int idLevantamentoNovo, int origem) {

        List<RiscoResultado> riscosNovos = repositorio.obterRiscos(idLevantamentoNovo);
        List<RiscoResultado> riscos = repositorio.obterRiscos(levantamento.id);

        for(int index = 0; index < riscos.size(); ++index){
            repositorio.duplicarMedidas(riscos.get(index).id, riscosNovos.get(index).id, origem);
        }

    }
}
