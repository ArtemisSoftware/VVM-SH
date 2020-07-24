package com.vvm.sh.databinding;

import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.vvm.sh.ui.ocorrencias.adaptadores.OcorrenciaRecyclerAdapter;
import com.vvm.sh.ui.ocorrencias.modelos.Ocorrencia;

import java.util.List;

public class OcorrenciasBinding {

    @BindingAdapter({"ocorrencias"})
    public static void setOcorrencias(RecyclerView view, List<Ocorrencia> items) {

        if(items == null){
            return;
        }

        RecyclerView.LayoutManager layoutManager = view.getLayoutManager();

        if(layoutManager == null){
            view.setLayoutManager(new LinearLayoutManager(view.getContext()));
        }

        OcorrenciaRecyclerAdapter adapter = (OcorrenciaRecyclerAdapter) view.getAdapter();

        if(adapter == null){
            adapter = new OcorrenciaRecyclerAdapter(view.getContext(), items);

            view.setAdapter(adapter);
        }
        else{
            adapter.atualizar(items);
        }

    }

}