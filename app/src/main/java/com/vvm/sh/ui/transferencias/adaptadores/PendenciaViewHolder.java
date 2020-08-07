package com.vvm.sh.ui.transferencias.adaptadores;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.vvm.sh.databinding.ItemPendenciaBinding;

public class PendenciaViewHolder extends RecyclerView.ViewHolder {

    public ItemPendenciaBinding binding;


    public PendenciaViewHolder(@NonNull View itemView) {
        super(itemView);

        binding = DataBindingUtil.bind(itemView);
    }
}
