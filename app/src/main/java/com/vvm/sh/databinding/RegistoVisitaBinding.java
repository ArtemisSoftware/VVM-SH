package com.vvm.sh.databinding;

import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.vvm.sh.baseDados.entidades.Tipo;
import com.vvm.sh.ui.registoVisita.adaptadores.OnRegistoVisitaListener;
import com.vvm.sh.ui.registoVisita.adaptadores.TrabalhoRealizadoRecyclerAdaptor;
import com.vvm.sh.ui.registoVisita.modelos.TrabalhoRealizado;

import java.util.List;

public class RegistoVisitaBinding {

    @BindingAdapter({"trabalhos" , "listener"})
    public static void setTrabalhosRealizados(RecyclerView view, List<TrabalhoRealizado> items, OnRegistoVisitaListener listener) {

        if(items == null){
            return;
        }

        RecyclerView.LayoutManager layoutManager = view.getLayoutManager();

        if(layoutManager == null){
            view.setLayoutManager(new LinearLayoutManager(view.getContext()));
        }

        TrabalhoRealizadoRecyclerAdaptor adapter = (TrabalhoRealizadoRecyclerAdaptor) view.getAdapter();

        if(adapter == null){
            adapter = new TrabalhoRealizadoRecyclerAdaptor(view.getContext(), items, listener);

            view.setAdapter(adapter);
        }
        else{
            adapter.atualizar(items);
        }

    }


}
