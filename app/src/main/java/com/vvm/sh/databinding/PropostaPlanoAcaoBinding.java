package com.vvm.sh.databinding;

import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.vvm.sh.ui.atividadesPendentes.relatorios.propostaPlanoAccao.adaptadores.OnPropostaPlanoAcaoListener;
import com.vvm.sh.ui.atividadesPendentes.relatorios.propostaPlanoAccao.adaptadores.PropostaRecyclerHolder;
import com.vvm.sh.ui.atividadesPendentes.relatorios.propostaPlanoAccao.modelos.Proposta;

import java.util.List;

public class PropostaPlanoAcaoBinding {

    @BindingAdapter({"propostas", "listener"})
    public static void setPropostas(RecyclerView view, List<Proposta> items, OnPropostaPlanoAcaoListener listener) {

        if(items == null){
            return;
        }

        RecyclerView.LayoutManager layoutManager = view.getLayoutManager();

        if(layoutManager == null){
            view.setLayoutManager(new LinearLayoutManager(view.getContext()));
        }

        PropostaRecyclerHolder adapter = (PropostaRecyclerHolder) view.getAdapter();

        if(adapter == null){
            adapter = new PropostaRecyclerHolder(view.getContext(), items, listener);

            view.setAdapter(adapter);
        }
        else{
            adapter.atualizar(items);
        }

    }


    @BindingAdapter({"propostas"})
    public static void setPropostas(RecyclerView view,  List<Proposta> items) {

        if(items == null){
            return;
        }

        RecyclerView.LayoutManager layoutManager = view.getLayoutManager();

        if(layoutManager == null){
            view.setLayoutManager(new LinearLayoutManager(view.getContext()));
        }

        PropostaRecyclerHolder adapter = (PropostaRecyclerHolder) view.getAdapter();

        if(adapter == null){
            adapter = new PropostaRecyclerHolder(view.getContext(), items);

            view.setAdapter(adapter);
        }
        else{
            adapter.atualizar(items);
        }

    }
}
