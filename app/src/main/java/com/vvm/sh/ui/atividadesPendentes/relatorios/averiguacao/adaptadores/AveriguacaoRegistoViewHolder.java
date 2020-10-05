package com.vvm.sh.ui.atividadesPendentes.relatorios.averiguacao.adaptadores;

import android.view.View;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.vvm.sh.R;
import com.vvm.sh.databinding.ItemAveriguacaoBinding;

public class AveriguacaoRegistoViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {


    public ItemAveriguacaoBinding binding;
    private OnAveriguacaoListener.OnAveriguacaoItemListener onItemListener;


    public AveriguacaoRegistoViewHolder(View itemView, OnAveriguacaoListener.OnAveriguacaoItemListener onItemListener) {
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