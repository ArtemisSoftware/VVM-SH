package com.vvm.sh.ui.atividadesPendentes.relatorios.checklist.adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.vvm.sh.R;
import com.vvm.sh.databinding.ItemChecklistBinding;
import com.vvm.sh.databinding.ItemFormandoBinding;
import com.vvm.sh.ui.atividadesPendentes.relatorios.Formando;
import com.vvm.sh.ui.atividadesPendentes.relatorios.FormandoViewHolder;
import com.vvm.sh.ui.atividadesPendentes.relatorios.OnFormacaoListener;
import com.vvm.sh.ui.atividadesPendentes.relatorios.checklist.modelos.Item;
import com.vvm.sh.util.metodos.PreferenciasUtil;

import java.util.ArrayList;
import java.util.List;

public class ItemRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{


    private List<Item> items = new ArrayList<>();
    private Context contexto;
    private OnChecklistListener listener;

    public ItemRecyclerAdapter(Context contexto, List<Item> items, OnChecklistListener listener) {
        this.items = items;
        this.contexto = contexto;
        this.listener = listener;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        ItemChecklistBinding binding = DataBindingUtil.inflate(LayoutInflater.from(contexto), R.layout.item_checklist, parent, false);
        return new ItemViewHolder(binding.getRoot(), listener);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        Item registo = items.get(position);
        ((ItemViewHolder)holder).binding.setItem(registo);

        ((ItemViewHolder)holder).binding.executePendingBindings();

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
