package com.vvm.sh.databinding;

import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.vvm.sh.baseDados.entidades.AtividadeExecutada;
import com.vvm.sh.ui.atividadesExecutadas.adaptadores.AtividadeExecutadaRecyclerAdapter;

import java.util.List;

public class AtividadesExecutadasBinding {


    @BindingAdapter({"atividadesExecutas"})
    public static void setRegistos(RecyclerView view, List<AtividadeExecutada> items) {

        if(items == null){
            return;
        }

        RecyclerView.LayoutManager layoutManager = view.getLayoutManager();

        if(layoutManager == null){
            view.setLayoutManager(new LinearLayoutManager(view.getContext()));
        }

        AtividadeExecutadaRecyclerAdapter adapter = (AtividadeExecutadaRecyclerAdapter) view.getAdapter();

        if(adapter == null){
            adapter = new AtividadeExecutadaRecyclerAdapter(view.getContext(), items);

            view.setAdapter(adapter);
        }
        else{
            adapter.atualizar(items);
        }

    }

}
