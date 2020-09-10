package com.vvm.sh.ui.quadroPessoal.adaptadores;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.vvm.sh.databinding.ItemColaboradorBinding;

public class ColaboradorViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public ItemColaboradorBinding binding;
    private OnColaboradorListener onItemListener;

    public ColaboradorViewHolder(@NonNull View itemView, OnColaboradorListener onItemListener) {
        super(itemView);
        binding = DataBindingUtil.bind(itemView);

        this.onItemListener = onItemListener;
        itemView.setOnClickListener(this);
    }



    @Override
    public void onClick(View v) {
        onItemListener.OnColaboradorClick(binding.getColaborador());
    }
}