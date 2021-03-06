package com.vvm.sh.ui.tarefa.adaptadores;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.vvm.sh.databinding.ItemOpcaoClienteBinding;

public class OpcaoClienteViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    ItemOpcaoClienteBinding binding;
    private OnTarefaListener onItemListener;

    public OpcaoClienteViewHolder(@NonNull View itemView, OnTarefaListener onItemListener) {
        super(itemView);
        binding = DataBindingUtil.bind(itemView);

        this.onItemListener = onItemListener;
        itemView.setOnClickListener(this);
    }



    @Override
    public void onClick(View v) {
        onItemListener.OnOpcaoItemListener(binding.getOpcao());
    }
}