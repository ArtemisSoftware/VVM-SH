package com.vvm.sh.ui.parqueExtintores.adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.vvm.sh.R;
import com.vvm.sh.databinding.ItemExtintorBinding;
import com.vvm.sh.ui.parqueExtintores.modelos.ExtintorRegisto;

import java.util.ArrayList;
import java.util.List;

public class ExtintorRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<ExtintorRegisto> items = new ArrayList<>();
    private Context contexto;
    private OnExtintoresListener listener;

    public ExtintorRecyclerAdapter(Context contexto, List<ExtintorRegisto> items, OnExtintoresListener listener) {
        this.items = items;
        this.contexto = contexto;
        this.listener = listener;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        ItemExtintorBinding binding = DataBindingUtil.inflate(LayoutInflater.from(contexto), R.layout.item_extintor, parent, false);
        return new ExtintorViewHolder(binding.getRoot(), listener);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        ExtintorRegisto registo = items.get(position);
        ((ExtintorViewHolder)holder).binding.setExtintor(registo);
        ((ExtintorViewHolder)holder).binding.executePendingBindings();

    }

    @Override
    public int getItemCount() {
        return items.size();
    }


    public void atualizar(List<ExtintorRegisto> items){
        this.items.clear();
        this.items.addAll(items);
        notifyDataSetChanged();
    }


}
