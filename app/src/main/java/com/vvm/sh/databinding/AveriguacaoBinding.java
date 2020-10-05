package com.vvm.sh.databinding;

import android.widget.TextView;

import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.vvm.sh.R;
import com.vvm.sh.ui.atividadesPendentes.relatorios.averiguacao.adaptadores.AveriguacaoRecyclerAdapter;
import com.vvm.sh.ui.atividadesPendentes.relatorios.averiguacao.adaptadores.AveriguacaoRegistoRecyclerAdapter;
import com.vvm.sh.ui.atividadesPendentes.relatorios.averiguacao.adaptadores.OnAveriguacaoListener;
import com.vvm.sh.ui.atividadesPendentes.relatorios.averiguacao.modelos.Averiguacao;
import com.vvm.sh.ui.atividadesPendentes.relatorios.averiguacao.modelos.AveriguacaoRegisto;
import com.vvm.sh.util.constantes.Identificadores;

import java.util.List;

public class AveriguacaoBinding {


    @BindingAdapter({"tipoAveriguacao"})
    public static void setTituloAveriguacao(TextView view, int tipo) {

        if(tipo == Identificadores.Relatorios.ID_RELATORIO_AVERIGUACAO_AVALIACAO_RISCO){
            view.setText(view.getContext().getString(R.string.avaliacaoRiscos));
        }
        else{
            view.setText(view.getContext().getString(R.string.auditoria));
        }
    }


    @BindingAdapter({"averiguacoes", "listener"})
    public static void setAveriguacoes(RecyclerView view, List<Averiguacao> items, OnAveriguacaoListener listener) {

        if(items == null){
            return;
        }

        RecyclerView.LayoutManager layoutManager = view.getLayoutManager();

        if(layoutManager == null){
            view.setLayoutManager(new LinearLayoutManager(view.getContext()));
        }

        AveriguacaoRecyclerAdapter adapter = (AveriguacaoRecyclerAdapter) view.getAdapter();

        if(adapter == null){
            adapter = new AveriguacaoRecyclerAdapter(view.getContext(), items, listener);

            view.setAdapter(adapter);
        }
        else{
            adapter.atualizar(items);
        }
    }



    @BindingAdapter({"averiguacaoRegistos", "listener"})
    public static void setAveriguacoesRegistos(RecyclerView view, List<AveriguacaoRegisto> items, OnAveriguacaoListener.OnAveriguacaoItemListener listener) {

        if(items == null){
            return;
        }

        RecyclerView.LayoutManager layoutManager = view.getLayoutManager();

        if(layoutManager == null){
            view.setLayoutManager(new LinearLayoutManager(view.getContext()));
        }

        AveriguacaoRegistoRecyclerAdapter adapter = (AveriguacaoRegistoRecyclerAdapter) view.getAdapter();

        if(adapter == null){
            adapter = new AveriguacaoRegistoRecyclerAdapter(view.getContext(), items, listener);

            view.setAdapter(adapter);
        }
        else{
            adapter.atualizar(items);
        }
    }

}
