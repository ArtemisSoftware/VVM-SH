package com.vvm.sh.ui.atividadesPendentes.relatorios.levantamentos.modelos;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.vvm.sh.R;
import com.vvm.sh.databinding.ItemLevantamentoBinding;
import com.vvm.sh.ui.atividadesPendentes.relatorios.levantamentos.OnLevantamentoListener;
import com.vvm.sh.util.metodos.PreferenciasUtil;

import java.util.ArrayList;
import java.util.List;

public class LevantamentoRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{


    private List<Levantamento> items = new ArrayList<>();
    private Context contexto;
    private OnLevantamentoListener.OnLevantamentoRegistoListener onItemListener;


    public LevantamentoRecyclerAdapter(Context contexto, List<Levantamento> items, OnLevantamentoListener.OnLevantamentoRegistoListener onItemListener) {
        this.items = items;
        this.contexto = contexto;
        this.onItemListener = onItemListener;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        ItemLevantamentoBinding binding = DataBindingUtil.inflate(LayoutInflater.from(contexto), R.layout.item_levantamento, parent, false);
        return new LevantamentoViewHolder(binding.getRoot(), this.onItemListener);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        Levantamento registo = items.get(position);
        ((LevantamentoViewHolder)holder).binding.setLevantamento(registo);
        ((LevantamentoViewHolder)holder).binding.setListener((OnLevantamentoListener.OnLevantamentoRegistoListener) contexto);
        ((LevantamentoViewHolder)holder).binding.executePendingBindings();


    }

    @Override
    public int getItemCount() {
        return items.size();
    }


    public void atualizar(List<Levantamento> items){
        this.items.clear();
        this.items.addAll(items);
        notifyDataSetChanged();
    }



}
