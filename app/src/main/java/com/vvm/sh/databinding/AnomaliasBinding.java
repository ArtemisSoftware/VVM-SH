package com.vvm.sh.databinding;

import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jaredrummler.materialspinner.MaterialSpinner;
import com.vvm.sh.ui.anomalias.adaptadores.AnomaliaRecyclerAdapter;
import com.vvm.sh.ui.anomalias.modelos.Anomalia;
import com.vvm.sh.ui.anomalias.modelos.AnomaliaResultado;
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


    @BindingAdapter({"tipos_", "anomalia"})
    public static void setTipos_(MaterialSpinner view, List<Tipo> registos, AnomaliaResultado resultado) {

        if (registos == null)
            return;

        view.setItems(registos);

        if(resultado != null) {

            for (int index = 0; index < registos.size(); ++index) {

                if(registos.get(index).id == resultado.idAnomalia){
                    view.setSelectedIndex(index);
                    break;
                }
            }
        }
    }

}
