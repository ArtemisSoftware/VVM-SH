package com.vvm.sh.ui.planoAccao.adaptadores;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.vvm.sh.databinding.ItemPlanoAcaoProgramacaoBinding;

public class PlanoAtividadeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public ItemPlanoAcaoProgramacaoBinding binding;
    private OnPlanoAtividadeListener listener;

    public PlanoAtividadeViewHolder(@NonNull View itemView, OnPlanoAtividadeListener listener) {
        super(itemView);
        binding = DataBindingUtil.bind(itemView);

        this.listener = listener;
        itemView.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {

        this.listener.OnAtividadeClick(binding.getAtividade());
    }
}