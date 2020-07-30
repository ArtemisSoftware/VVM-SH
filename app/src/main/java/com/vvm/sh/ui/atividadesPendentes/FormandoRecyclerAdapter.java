package com.vvm.sh.ui.atividadesPendentes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.vvm.sh.R;
import com.vvm.sh.databinding.ItemFormandoBinding;
import com.vvm.sh.ui.atividadesPendentes.relatorios.Formando;
import com.vvm.sh.ui.atividadesPendentes.relatorios.OnFormacaoListener;
import com.vvm.sh.util.adaptadores.ItemRecyclerAdapter;
import com.vvm.sh.util.adaptadores.ItemViewHolder;
import com.vvm.sh.util.interfaces.OnCheckBoxItemListener;
import com.vvm.sh.util.interfaces.OnItemListener;

import java.util.ArrayList;
import java.util.List;

public class FormandoRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{


    private List<Formando> items = new ArrayList<>();
    private Context contexto;
    private OnFormacaoListener listener;

    public FormandoRecyclerAdapter(Context contexto, List<Formando> items, OnFormacaoListener listener) {
        this.items = items;
        this.contexto = contexto;
        this.listener = listener;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        ItemFormandoBinding binding = DataBindingUtil.inflate(LayoutInflater.from(contexto), R.layout.item_formando, parent, false);
        return new FormandoViewHolder(binding.getRoot(), listener);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        Formando registo = items.get(position);
        ((FormandoViewHolder)holder).binding.setFormando(registo);
        ((FormandoViewHolder)holder).binding.setListener(listener);
        ((FormandoViewHolder)holder).binding.executePendingBindings();

    }

    @Override
    public int getItemCount() {
        return items.size();
    }


    public void atualizar(List<Formando> items){
        this.items.clear();
        this.items.addAll(items);
        notifyDataSetChanged();
    }


}
