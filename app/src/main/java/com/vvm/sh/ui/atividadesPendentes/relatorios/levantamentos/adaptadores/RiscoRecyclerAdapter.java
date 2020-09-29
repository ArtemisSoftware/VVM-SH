package com.vvm.sh.ui.atividadesPendentes.relatorios.levantamentos.adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.vvm.sh.R;
import com.vvm.sh.databinding.ItemRiscoBinding;
import com.vvm.sh.ui.atividadesPendentes.relatorios.levantamentos.modelos.Risco;

import java.util.ArrayList;
import java.util.List;

public class RiscoRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{


    private List<Risco> items = new ArrayList<>();
    private Context contexto;
    private OnLevantamentoListener.OnRiscoListener onItemListener;


    public RiscoRecyclerAdapter(Context contexto, List<Risco> items, OnLevantamentoListener.OnRiscoListener onItemListener) {
        this.items = items;
        this.contexto = contexto;
        this.onItemListener = onItemListener;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        ItemRiscoBinding binding = DataBindingUtil.inflate(LayoutInflater.from(contexto), R.layout.item_risco, parent, false);
        return new RiscoViewHolder(binding.getRoot(), this.onItemListener);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        Risco registo = items.get(position);
        ((RiscoViewHolder)holder).binding.setRisco(registo);
        ((RiscoViewHolder)holder).binding.setListener((OnLevantamentoListener.OnRiscoListener) contexto);
        ((RiscoViewHolder)holder).binding.executePendingBindings();


    }

    @Override
    public int getItemCount() {
        return items.size();
    }


    public void atualizar(List<Risco> items){
        this.items.clear();
        this.items.addAll(items);
        notifyDataSetChanged();
    }



}
