package com.vvm.sh.databinding;

import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jaredrummler.materialspinner.MaterialSpinner;
import com.vvm.sh.ui.anomalias.adaptadores.AnomaliaRecyclerAdapter;
import com.vvm.sh.ui.anomalias.adaptadores.AnomaliaRegistadaRecyclerAdapter;
import com.vvm.sh.ui.anomalias.adaptadores.OnAnomaliasListener;
import com.vvm.sh.baseDados.entidades.Anomalia;
import com.vvm.sh.ui.anomalias.modelos.AnomaliaRegistada;
import com.vvm.sh.baseDados.entidades.AnomaliaResultado;
import com.vvm.sh.ui.opcoes.modelos.Tipo;

import java.util.List;

public class AnomaliasBinding {

    @BindingAdapter({"anomalias"})
    public static void setAnomalias(RecyclerView view, List<Anomalia> items) {

        if(items == null){
            return;
        }

        RecyclerView.LayoutManager layoutManager = view.getLayoutManager();

        if(layoutManager == null){
            view.setLayoutManager(new LinearLayoutManager(view.getContext()));
        }

        AnomaliaRecyclerAdapter adapter = (AnomaliaRecyclerAdapter) view.getAdapter();

        if(adapter == null){
            adapter = new AnomaliaRecyclerAdapter(view.getContext(), items);

            view.setAdapter(adapter);
        }
        else{
            adapter.atualizar(items);
        }

    }


    @BindingAdapter({"anomaliasRegistadas" , "listener"})
    public static void setAnomaliasRegistadas(RecyclerView view, List<AnomaliaRegistada> items, OnAnomaliasListener listener) {

        if(items == null){
            return;
        }

        RecyclerView.LayoutManager layoutManager = view.getLayoutManager();

        if(layoutManager == null){
            view.setLayoutManager(new LinearLayoutManager(view.getContext()));
        }

        AnomaliaRegistadaRecyclerAdapter adapter = (AnomaliaRegistadaRecyclerAdapter) view.getAdapter();

        if(adapter == null){
            adapter = new AnomaliaRegistadaRecyclerAdapter(view.getContext(), items, listener);

            view.setAdapter(adapter);
        }
        else{
            adapter.atualizar(items);
        }

    }



    @BindingAdapter({"tipos_", "anomalia"})
    public static void setTipos_(MaterialSpinner view, List<Tipo> registos, AnomaliaRegistada registo) {

        if (registos == null)
            return;

        view.setItems(registos);

        if(registo != null) {

            for (int index = 0; index < registos.size(); ++index) {

                if(registos.get(index).id == registo.resultado.idAnomalia){
                    view.setSelectedIndex(index);
                    break;
                }
            }
        }
    }

}
