package com.vvm.sh.ui.atividadesPendentes.relatorios.checklist.adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.vvm.sh.R;
import com.vvm.sh.databinding.ItemChecklistAreaBinding;
import com.vvm.sh.databinding.ItemChecklistSeccaoBinding;
import com.vvm.sh.ui.atividadesPendentes.relatorios.checklist.modelos.Item;
import com.vvm.sh.util.constantes.Identificadores;

import java.util.ArrayList;
import java.util.List;

public class ItemRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{


    private List<Item> items = new ArrayList<>();
    private Context contexto;
    private OnChecklistListener.OnItemListener listener;

    public ItemRecyclerAdapter(Context contexto, List<Item> items, OnChecklistListener.OnItemListener listener) {
        this.items = items;
        this.contexto = contexto;
        this.listener = listener;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


        switch (viewType){

            case Identificadores.Checklist.TIPO_AREA:


                ItemChecklistAreaBinding binding = DataBindingUtil.inflate(LayoutInflater.from(contexto), R.layout.item_checklist_area, parent, false);
                return new AreaViewHolder(binding.getRoot(), listener);


            case Identificadores.Checklist.TIPO_SECCAO:

                ItemChecklistSeccaoBinding bindingSeccao = DataBindingUtil.inflate(LayoutInflater.from(contexto), R.layout.item_checklist_seccao, parent, false);
                return new AreaViewHolder(bindingSeccao.getRoot(), listener);

        }

        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        Item registo = items.get(position);

        switch (registo.tipo) {

            case Identificadores.Checklist.TIPO_AREA:

                ((AreaViewHolder)holder).binding.setItem(registo);
                ((AreaViewHolder)holder).binding.executePendingBindings();
                break;


            case Identificadores.Checklist.TIPO_SECCAO:

                ((SeccaoViewHolder)holder).binding.setItem(registo);
                ((SeccaoViewHolder)holder).binding.executePendingBindings();
                break;

            default:
                break;

        }





    }


    @Override
    public int getItemViewType(int position) {
        return items.get(position).tipo;
    }

    @Override
    public int getItemCount() {
        return items.size();
    }


    public void atualizar(List<Item> items){
        this.items.clear();
        this.items.addAll(items);
        notifyDataSetChanged();
    }



}
