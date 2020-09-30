package com.vvm.sh.ui.planoAccao.adaptadores;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.vvm.sh.databinding.ItemPlanoAcaoNotaBinding;
import com.vvm.sh.databinding.ItemPlanoAcaoProgramacaoBinding;
import com.vvm.sh.util.constantes.Identificadores;

public class PlanoAtividadeNotaViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public ItemPlanoAcaoNotaBinding binding;
    private OnPlanoAtividadeListener listener;


    public PlanoAtividadeNotaViewHolder(@NonNull View itemView, OnPlanoAtividadeListener listener) {
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