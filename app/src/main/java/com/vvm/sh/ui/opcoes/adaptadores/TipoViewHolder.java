package com.vvm.sh.ui.opcoes.adaptadores;

import android.view.View;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.vvm.sh.databinding.ItemTipoBinding;

public class TipoViewHolder extends RecyclerView.ViewHolder implements View.OnLongClickListener{

    ItemTipoBinding binding;
    private OnTipoListener onItemLongListener;


    public TipoViewHolder(View itemView, OnTipoListener onItemLongListener) {
        super(itemView);
        binding = DataBindingUtil.bind(itemView);

        this.onItemLongListener = onItemLongListener;
        itemView.setOnLongClickListener(this);
    }

    @Override
    public boolean onLongClick(View v) {
        onItemLongListener.OnTipoLongPressListener(binding.getTipo().obterDescricao());//onItemLongClick(/*getAdapterPosition()*/);
        return true;
    }

}