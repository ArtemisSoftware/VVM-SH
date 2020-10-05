package com.vvm.sh.ui.atividadesPendentes.relatorios.levantamentos.adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.vvm.sh.R;
import com.vvm.sh.databinding.ItemCategoriaProfissionalBinding;
import com.vvm.sh.ui.atividadesPendentes.relatorios.levantamentos.modelos.CategoriaProfissional;

import java.util.ArrayList;
import java.util.List;

public class CategoriaProfissionalRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{


    private List<CategoriaProfissional> items = new ArrayList<>();
    private Context contexto;
    private OnLevantamentoListener.OnCategoriaProfissionalListener onItemListener;


    public CategoriaProfissionalRecyclerAdapter(Context contexto, List<CategoriaProfissional> items, OnLevantamentoListener.OnCategoriaProfissionalListener onItemListener) {
        this.items = items;
        this.contexto = contexto;
        this.onItemListener = onItemListener;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        ItemCategoriaProfissionalBinding binding = DataBindingUtil.inflate(LayoutInflater.from(contexto), R.layout.item_categoria_profissional, parent, false);
        return new CategoriaProfissionalViewHolder(binding.getRoot(), this.onItemListener);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        CategoriaProfissional registo = items.get(position);
        ((CategoriaProfissionalViewHolder)holder).binding.setCategoria(registo);
        ((CategoriaProfissionalViewHolder)holder).binding.setListener((OnLevantamentoListener.OnCategoriaProfissionalListener) contexto);
        ((CategoriaProfissionalViewHolder)holder).binding.executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return items.size();
    }


    public void atualizar(List<CategoriaProfissional> items){
        this.items.clear();
        this.items.addAll(items);
        notifyDataSetChanged();
    }


}
