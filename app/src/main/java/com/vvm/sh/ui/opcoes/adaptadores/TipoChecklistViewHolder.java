package com.vvm.sh.ui.opcoes.adaptadores;

import android.view.View;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.vvm.sh.databinding.ItemTipoBinding;
import com.vvm.sh.databinding.ItemTipoChecklistBinding;

public class TipoChecklistViewHolder extends RecyclerView.ViewHolder implements View.OnLongClickListener{

    ItemTipoChecklistBinding binding;
    private OnTipoListener onItemLongListener;


    public TipoChecklistViewHolder(View itemView, OnTipoListener onItemLongListener) {
        super(itemView);
        binding = DataBindingUtil.bind(itemView);

        this.onItemLongListener = onItemLongListener;
        itemView.setOnLongClickListener(this);
    }

    @Override
    public boolean onLongClick(View v) {
        onItemLongListener.OnTipoLongPressListener(binding.getTipo());//onItemLongClick(/*getAdapterPosition()*/);
        return true;
    }

}