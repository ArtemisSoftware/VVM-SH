package com.vvm.sh.ui.crossSelling.adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.vvm.sh.R;
import com.vvm.sh.databinding.ItemCrossSellingBinding;
import com.vvm.sh.ui.opcoes.modelos.Tipo;

import java.util.ArrayList;
import java.util.List;


public class CrossSellingRecyclerAdapter  extends RecyclerView.Adapter<RecyclerView.ViewHolder>{


    private List<Tipo> items = new ArrayList<>();
    private Context contexto;
    private OnCrossSellingListener onItemListener;


    public CrossSellingRecyclerAdapter(Context contexto, List<Tipo> items, OnCrossSellingListener onItemListener) {
        this.items = items;
        this.contexto = contexto;
        this.onItemListener = onItemListener;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        ItemCrossSellingBinding binding = DataBindingUtil.inflate(LayoutInflater.from(contexto), R.layout.item_cross_selling, parent, false);
        return new CrossSellingViewHolder(binding.getRoot(), this.onItemListener);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        Tipo registo = items.get(position);
        ((CrossSellingViewHolder)holder).binding.setCrossSelling(registo);
        ((CrossSellingViewHolder)holder).binding.setListener((OnCrossSellingListener) contexto);

        ((CrossSellingViewHolder)holder).binding.executePendingBindings();


    }

    @Override
    public int getItemCount() {
        return items.size();
    }


    public void atualizar(List<Tipo> items){
        this.items.clear();
        this.items.addAll(items);
        notifyDataSetChanged();
    }



}