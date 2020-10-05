package com.vvm.sh.ui.atividadesPendentes.relatorios.avaliacaoAmbiental;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.vvm.sh.databinding.ItemAvaliacaoTemperaturaHumidadeBinding;

public class TemperaturaHumidadeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    public ItemAvaliacaoTemperaturaHumidadeBinding binding;

    private OnAvaliacaoAmbientalListener listener;


    public TemperaturaHumidadeViewHolder(@NonNull View itemView, OnAvaliacaoAmbientalListener listener) {
        super(itemView);

        binding = DataBindingUtil.bind(itemView);

        this.listener = listener;
        itemView.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        this.listener.OnAvaliacaoClick(binding.getAvaliacao().resultado);
    }
}
