package com.vvm.sh.databinding;

import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jaredrummler.materialspinner.MaterialSpinner;
import com.vvm.sh.baseDados.entidades.AreaChecklist;
import com.vvm.sh.baseDados.entidades.Tipo;
import com.vvm.sh.ui.atividadesPendentes.relatorios.checklist.adaptadores.ItemRecyclerAdapter;
import com.vvm.sh.ui.atividadesPendentes.relatorios.checklist.adaptadores.OnChecklistListener;
import com.vvm.sh.ui.atividadesPendentes.relatorios.checklist.modelos.Item;

import java.util.List;

public class ChecklistBinding {


    @BindingAdapter({"itens",  "listener"})
    public static void setChecklist(RecyclerView view, List<Item> items, OnChecklistListener listener) {

        if(items == null){
            return;
        }

        RecyclerView.LayoutManager layoutManager = view.getLayoutManager();

        if(layoutManager == null){
            view.setLayoutManager(new LinearLayoutManager(view.getContext()));
        }

        ItemRecyclerAdapter adapter = (ItemRecyclerAdapter) view.getAdapter();

        if(adapter == null){
            adapter = new ItemRecyclerAdapter(view.getContext(), items, listener);

            view.setAdapter(adapter);
        }
        else{
            adapter.atualizar(items);
        }

    }


    @BindingAdapter({"areas", "idArea"})
    public static void setAreas(MaterialSpinner view, List<AreaChecklist> registos, int id) {

        if (registos == null)
            return;

        view.setItems(registos);

        if(id != 0) {

            for (int index = 0; index < registos.size(); ++index) {

                if(registos.get(index).idArea == id){
                    view.setSelectedIndex(index);
                    break;
                }
            }
        }
    }


}
