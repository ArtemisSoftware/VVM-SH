package com.vvm.sh.ui.atividadesPendentes.relatorios.averiguacao.adaptadores;

import android.view.View;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.vvm.sh.databinding.ItemAveriguacaoAreaBinding;

public class AveriguacaoViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {


    public ItemAveriguacaoAreaBinding binding;
    private OnAveriguacaoListener onItemListener;


    public AveriguacaoViewHolder(View itemView, OnAveriguacaoListener onItemListener) {
        super(itemView);
        binding = DataBindingUtil.bind(itemView);

        this.onItemListener = onItemListener;
        itemView.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {

        onItemListener.OnItemClick(binding.getAveriguacao());
    }
}