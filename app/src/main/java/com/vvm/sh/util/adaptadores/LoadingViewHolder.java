package com.vvm.sh.util.adaptadores;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.vvm.sh.databinding.ItemLoadingBinding;

public class LoadingViewHolder extends RecyclerView.ViewHolder {

    public ItemLoadingBinding binding;

    public LoadingViewHolder(@NonNull View itemView) {
        super(itemView);
        binding = DataBindingUtil.bind(itemView);
    }

}