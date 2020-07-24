package com.vvm.sh.ui.atividadesPendentes.adaptadores;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.vvm.sh.databinding.ItemAtividadePendenteBinding;


public class AtividadePendenteViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    public ItemAtividadePendenteBinding binding;

    private OnAtividadePendenteListener listener;

    public AtividadePendenteViewHolder(@NonNull View itemView,  OnAtividadePendenteListener listener) {
        super(itemView);

        binding = DataBindingUtil.bind(itemView);


        this.listener = listener;
        itemView.setOnClickListener(this);

    }



    @Override
    public void onClick(View v) {
        listener.OnAtividadeClick(binding.getAtividade());
    }


}
