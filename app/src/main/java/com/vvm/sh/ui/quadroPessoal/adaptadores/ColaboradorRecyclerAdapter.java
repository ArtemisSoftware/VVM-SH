package com.vvm.sh.ui.quadroPessoal.adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.vvm.sh.R;
import com.vvm.sh.databinding.ItemColaboradorBinding;
import com.vvm.sh.ui.quadroPessoal.modelos.ColaboradorRegisto;

import java.util.ArrayList;
import java.util.List;

public class ColaboradorRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    private List<ColaboradorRegisto> items = new ArrayList<>();
    private Context contexto;
    private OnColaboradorListener onItemListener;

    public ColaboradorRecyclerAdapter(Context contexto, List<ColaboradorRegisto> items, OnColaboradorListener onItemListener) {
        this.items = items;
        this.contexto = contexto;
        this.onItemListener = onItemListener;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        ItemColaboradorBinding binding = DataBindingUtil.inflate(LayoutInflater.from(contexto), R.layout.item_colaborador, parent, false);
        return new ColaboradorViewHolder(binding.getRoot(), this.onItemListener);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        ColaboradorRegisto registo = items.get(position);
        ((ColaboradorViewHolder) holder).binding.setColaborador(registo);
        ((ColaboradorViewHolder) holder).binding.setListener((OnColaboradorListener) contexto);

        ((ColaboradorViewHolder) holder).binding.executePendingBindings();

    }

    @Override
    public int getItemCount() {
        return items.size();
    }


    public void atualizar(List<ColaboradorRegisto> items) {
        this.items.clear();
        this.items.addAll(items);
        notifyDataSetChanged();
    }

}