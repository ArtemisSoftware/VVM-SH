package com.vvm.sh.databinding;

import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.vvm.sh.ui.ocorrencias.adaptadores.OcorrenciaHistoricoRecyclerAdapter;
import com.vvm.sh.ui.ocorrencias.adaptadores.OcorrenciaRecyclerAdapter;
import com.vvm.sh.ui.ocorrencias.adaptadores.OcorrenciaRegistoRecyclerAdapter;
import com.vvm.sh.ui.ocorrencias.adaptadores.OnOcorrenciaListener;
import com.vvm.sh.ui.ocorrencias.adaptadores.OnOcorrenciaRegistoListener;
import com.vvm.sh.baseDados.entidades.Ocorrencia;
import com.vvm.sh.baseDados.entidades.OcorrenciaHistorico;
import com.vvm.sh.ui.ocorrencias.modelos.OcorrenciaRegisto;

import java.util.List;

public class OcorrenciasBinding {

    @BindingAdapter({"ocorrencias", "listener"})
    public static void setOcorrencias(RecyclerView view, List<Ocorrencia> items, OnOcorrenciaListener listener) {

        if(items == null){
            return;
        }

        RecyclerView.LayoutManager layoutManager = view.getLayoutManager();

        if(layoutManager == null){
            view.setLayoutManager(new LinearLayoutManager(view.getContext()));
        }

        OcorrenciaRecyclerAdapter adapter = (OcorrenciaRecyclerAdapter) view.getAdapter();

        if(adapter == null){
            adapter = new OcorrenciaRecyclerAdapter(view.getContext(), items, listener);

            view.setAdapter(adapter);
        }
        else{
            adapter.atualizar(items);
        }
    }




    @BindingAdapter({"registosOcorrencias", "onItemClick"})
    public static void setRegistosOcorrencias(RecyclerView view, List<OcorrenciaRegisto> items, OnOcorrenciaRegistoListener listener) {

        if(items == null){
            return;
        }

        RecyclerView.LayoutManager layoutManager = view.getLayoutManager();

        if(layoutManager == null){
            view.setLayoutManager(new LinearLayoutManager(view.getContext()));
        }

        OcorrenciaRegistoRecyclerAdapter adapter = (OcorrenciaRegistoRecyclerAdapter) view.getAdapter();

        if(adapter == null){
            adapter = new OcorrenciaRegistoRecyclerAdapter(view.getContext(), items, listener);

            view.setAdapter(adapter);
        }
        else{
            adapter.atualizar(items);
        }
    }



    @BindingAdapter({"registosOcorrencias", "onItemClick"})
    public static void setRegistosOcorrencias(RecyclerView view, List<OcorrenciaRegisto> items, OnOcorrenciaListener listener) {

        if(items == null){
            return;
        }

        RecyclerView.LayoutManager layoutManager = view.getLayoutManager();

        if(layoutManager == null){
            view.setLayoutManager(new LinearLayoutManager(view.getContext()));
        }

        OcorrenciaRegistoRecyclerAdapter adapter = (OcorrenciaRegistoRecyclerAdapter) view.getAdapter();

        if(adapter == null){
            adapter = new OcorrenciaRegistoRecyclerAdapter(view.getContext(), items, listener);

            view.setAdapter(adapter);
        }
        else{
            adapter.atualizar(items);
        }
    }



    @BindingAdapter({"historico"})
    public static void setHistorico(RecyclerView view, List<OcorrenciaHistorico> items) {

        if(items == null){
            return;
        }

        RecyclerView.LayoutManager layoutManager = view.getLayoutManager();

        if(layoutManager == null){
            view.setLayoutManager(new LinearLayoutManager(view.getContext()));
        }

        OcorrenciaHistoricoRecyclerAdapter adapter = (OcorrenciaHistoricoRecyclerAdapter) view.getAdapter();

        if(adapter == null){
            adapter = new OcorrenciaHistoricoRecyclerAdapter(view.getContext(), items);

            view.setAdapter(adapter);
        }
        else{
            adapter.atualizar(items);
        }
    }

}
