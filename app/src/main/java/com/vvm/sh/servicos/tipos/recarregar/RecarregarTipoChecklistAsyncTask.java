package com.vvm.sh.servicos.tipos.recarregar;

import android.app.Activity;
import android.database.sqlite.SQLiteConstraintException;
import android.os.AsyncTask;
import android.os.Handler;

import com.vvm.sh.api.modelos.pedido.ITipoChecklist;
import com.vvm.sh.baseDados.VvmshBaseDados;
import com.vvm.sh.baseDados.entidades.AreaChecklist;
import com.vvm.sh.baseDados.entidades.Atualizacao;
import com.vvm.sh.baseDados.entidades.CheckList;
import com.vvm.sh.baseDados.entidades.ItemChecklist;
import com.vvm.sh.baseDados.entidades.SeccaoChecklist;
import com.vvm.sh.repositorios.CarregamentoTiposRepositorio;
import com.vvm.sh.repositorios.TiposRepositorio;
import com.vvm.sh.servicos.tipos.TipoChecklistAsyncTask;
import com.vvm.sh.ui.transferencias.adaptadores.OnTransferenciaListener;
import com.vvm.sh.util.AtualizacaoUI;
import com.vvm.sh.util.AtualizacaoUI_;
import com.vvm.sh.util.constantes.Sintaxe;
import com.vvm.sh.util.mapeamento.DownloadMapping;
import com.vvm.sh.util.metodos.MensagensUtil;

import java.util.ArrayList;
import java.util.List;

public class RecarregarTipoChecklistAsyncTask extends TipoChecklistAsyncTask {

    private MensagensUtil dialogo;

    public RecarregarTipoChecklistAsyncTask(Activity activity, VvmshBaseDados vvmshBaseDados, CarregamentoTiposRepositorio repositorio) {
        super(vvmshBaseDados, repositorio);

        dialogo = new MensagensUtil(activity);
    }


    @Override
    protected void onPreExecute() {
        dialogo.progresso(true, Sintaxe.Frases.CARREGAR_CHECKLISTS);
    }


    @Override
    protected void onPostExecute(List<Object> iTipoListagems) {
        dialogo.terminarProgresso();
    }

        @Override
    protected void atualizarUI(int index, int limite, Atualizacao atualizacao) {

    }

}
