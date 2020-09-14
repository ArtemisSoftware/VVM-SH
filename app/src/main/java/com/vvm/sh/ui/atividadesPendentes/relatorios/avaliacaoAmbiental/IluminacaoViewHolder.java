package com.vvm.sh.ui.atividadesPendentes.relatorios.avaliacaoAmbiental;

import android.view.View;
import android.widget.CompoundButton;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.vvm.sh.databinding.ItemAvaliacaoIluminacaoBinding;

public class IluminacaoViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    public ItemAvaliacaoIluminacaoBinding binding;

    private OnAvaliacaoAmbientalListener listener;


    public IluminacaoViewHolder(@NonNull View itemView, OnAvaliacaoAmbientalListener listener) {
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
