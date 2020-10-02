package com.vvm.sh.ui.atividadesPendentes.relatorios.averiguacao.adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.vvm.sh.R;
import com.vvm.sh.databinding.ItemAveriguacaoBinding;
import com.vvm.sh.ui.atividadesPendentes.relatorios.averiguacao.modelos.AveriguacaoRegisto;
import com.vvm.sh.util.metodos.PreferenciasUtil;

import java.util.ArrayList;
import java.util.List;

public class AveriguacaoRegistoRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    private List<AveriguacaoRegisto> items = new ArrayList<>();
    private Context contexto;
    private OnAveriguacaoListener.OnAveriguacaoItemListener listener;

    public AveriguacaoRegistoRecyclerAdapter(Context contexto, List<AveriguacaoRegisto> items, OnAveriguacaoListener.OnAveriguacaoItemListener listener) {
        this.items = items;
        this.contexto = contexto;
        this.listener = listener;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        ItemAveriguacaoBinding binding = DataBindingUtil.inflate(LayoutInflater.from(contexto), R.layout.item_averiguacao, parent, false);
        return new AveriguacaoRegistoViewHolder(binding.getRoot(), listener);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        AveriguacaoRegisto registo = items.get(position);
        ((AveriguacaoRegistoViewHolder) holder).binding.setAveriguacao(registo);
        ((AveriguacaoRegistoViewHolder) holder).binding.executePendingBindings();

    }

    @Override
    public int getItemCount() {
        return items.size();
    }


    public void atualizar(List<AveriguacaoRegisto> items) {
        this.items.clear();
        this.items.addAll(items);
        notifyDataSetChanged();
    }
}