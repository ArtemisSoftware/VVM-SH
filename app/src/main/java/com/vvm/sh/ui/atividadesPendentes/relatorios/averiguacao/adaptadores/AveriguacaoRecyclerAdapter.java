package com.vvm.sh.ui.atividadesPendentes.relatorios.averiguacao.adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.vvm.sh.R;
import com.vvm.sh.databinding.ItemAveriguacaoAreaBinding;
import com.vvm.sh.ui.atividadesPendentes.relatorios.averiguacao.modelos.Averiguacao;

import java.util.ArrayList;
import java.util.List;

public class AveriguacaoRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    private List<Averiguacao> items = new ArrayList<>();
    private Context contexto;
    private OnAveriguacaoListener listener;

    public AveriguacaoRecyclerAdapter(Context contexto, List<Averiguacao> items, OnAveriguacaoListener listener) {
        this.items = items;
        this.contexto = contexto;
        this.listener = listener;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        ItemAveriguacaoAreaBinding binding = DataBindingUtil.inflate(LayoutInflater.from(contexto), R.layout.item_averiguacao_area, parent, false);
        return new AveriguacaoViewHolder(binding.getRoot(), listener);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        Averiguacao registo = items.get(position);
        ((AveriguacaoViewHolder) holder).binding.setAveriguacao(registo);
        ((AveriguacaoViewHolder) holder).binding.executePendingBindings();

    }

    @Override
    public int getItemCount() {
        return items.size();
    }


    public void atualizar(List<Averiguacao> items) {
        this.items.clear();
        this.items.addAll(items);
        notifyDataSetChanged();
    }
}