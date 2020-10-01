package com.vvm.sh.databinding;

import android.widget.TextView;

import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.vvm.sh.ui.planoAccao.adaptadores.OnPlanoAtividadeListener;
import com.vvm.sh.ui.planoAccao.adaptadores.PlanoAtividadeRecyclerAdapter;
import com.vvm.sh.ui.planoAccao.modelo.AtividadeRegisto;

import java.util.List;

public class PlanoAcaoBinding {


    @BindingAdapter({"atividades", "listener", "ano"})
    public static void setAtividadesPlano(RecyclerView view, List<AtividadeRegisto> items, OnPlanoAtividadeListener listener, int ano) {

        if (items == null) {
            return;
        }

        if (ano == 0) {
            return;
        }

        RecyclerView.LayoutManager layoutManager = view.getLayoutManager();

        if (layoutManager == null) {
            view.setLayoutManager(new LinearLayoutManager(view.getContext()));
        }

        PlanoAtividadeRecyclerAdapter adapter = (PlanoAtividadeRecyclerAdapter) view.getAdapter();

        if (adapter == null) {
            adapter = new PlanoAtividadeRecyclerAdapter(view.getContext(), items, listener, ano);

            view.setAdapter(adapter);
        } else {
            adapter.atualizar(items);
        }
    }


    @BindingAdapter({"atividadePlano", "ano", "mes"})
    public static void setEstadoMes(TextView view, AtividadeRegisto item, int ano, int mes) {

        view.setTag(mes);
        view.setBackgroundResource(item.obterCorExecucao(ano, mes - 1));

    }
}