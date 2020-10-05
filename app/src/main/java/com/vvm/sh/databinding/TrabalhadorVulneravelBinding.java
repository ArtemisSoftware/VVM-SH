package com.vvm.sh.databinding;

import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.vvm.sh.ui.atividadesPendentes.relatorios.trabalhadoresVulneraveis.adaptadores.OnTrabalhadorVulneravelListener;
import com.vvm.sh.ui.atividadesPendentes.relatorios.trabalhadoresVulneraveis.adaptadores.TrabalhadorVulneravelRecyclerAdapter;
import com.vvm.sh.ui.atividadesPendentes.relatorios.trabalhadoresVulneraveis.modelos.TrabalhadorVulneravel;

import java.util.List;

public class TrabalhadorVulneravelBinding {


    @BindingAdapter({"vulnerabilidade" , "listener"})
    public static void setVulnerabilidades(RecyclerView view, List<TrabalhadorVulneravel> items, OnTrabalhadorVulneravelListener listener) {

        if(items == null){
            return;
        }

        RecyclerView.LayoutManager layoutManager = view.getLayoutManager();

        if(layoutManager == null){
            view.setLayoutManager(new LinearLayoutManager(view.getContext()));
        }

        TrabalhadorVulneravelRecyclerAdapter adapter = (TrabalhadorVulneravelRecyclerAdapter) view.getAdapter();

        if(adapter == null){
            adapter = new TrabalhadorVulneravelRecyclerAdapter(view.getContext(), items, listener);

            view.setAdapter(adapter);
        }
        else{
            adapter.atualizar(items);
        }

    }

}
