package com.vvm.sh.util.adaptadores;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.vvm.sh.databinding.ItemExaustoBinding;

public class ExaustoViewHolder extends RecyclerView.ViewHolder {


    public ItemExaustoBinding binding;

    public ExaustoViewHolder(@NonNull View itemView) {
        super(itemView);
        binding = DataBindingUtil.bind(itemView);
    }
}