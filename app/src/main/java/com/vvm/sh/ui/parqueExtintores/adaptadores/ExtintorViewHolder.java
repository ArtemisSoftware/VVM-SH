package com.vvm.sh.ui.parqueExtintores.adaptadores;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.vvm.sh.databinding.ItemExtintorBinding;
import com.vvm.sh.util.metodos.PreferenciasUtil;

public class ExtintorViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{


    public ItemExtintorBinding binding;

    private OnExtintoresListener listener;


    public ExtintorViewHolder(@NonNull View itemView, OnExtintoresListener listener) {
        super(itemView);

        binding = DataBindingUtil.bind(itemView);

        this.listener = listener;

        if(PreferenciasUtil.agendaEditavel(itemView.getContext()) == true) {
            itemView.setOnClickListener(this);
        }
    }

    @Override
    public void onClick(View v) {
        this.listener.OnExtintorClick(binding.getExtintor());
    }
}
