package com.vvm.sh.databinding;

import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jaredrummler.materialspinner.MaterialSpinner;
import com.vvm.sh.ui.atividadesPendentes.adaptadores.AtividadePendenteRecyclerAdapter;
import com.vvm.sh.ui.atividadesPendentes.adaptadores.OnAtividadePendenteListener;
import com.vvm.sh.ui.atividadesPendentes.modelos.AtividadePendente;
import com.vvm.sh.ui.atividadesPendentes.modelos.AtividadePendenteResultado;
import com.vvm.sh.ui.opcoes.modelos.Tipo;

import java.util.List;

public class AtividadesPendentesBinding {

    @BindingAdapter({"atividadesPendentes", "onItemClick"})
    public static void setAtividadesPendentes(RecyclerView view, List<AtividadePendente> items, OnAtividadePendenteListener listener) {

        if(items == null){
            return;
        }

        RecyclerView.LayoutManager layoutManager = view.getLayoutManager();

        if(layoutManager == null){
            view.setLayoutManager(new LinearLayoutManager(view.getContext()));
        }

        AtividadePendenteRecyclerAdapter adapter = (AtividadePendenteRecyclerAdapter) view.getAdapter();

        if(adapter == null){
            adapter = new AtividadePendenteRecyclerAdapter(view.getContext(), items, listener);

            view.setAdapter(adapter);
        }
        else{
            adapter.atualizar(items);
        }

    }


    @BindingAdapter({"tipos_", "atividade"})
    public static void setTipos_(MaterialSpinner view, List<Tipo> registos, AtividadePendenteResultado resultado) {

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
