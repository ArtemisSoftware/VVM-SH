package com.vvm.sh.ui.upload.adaptadores;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.vvm.sh.databinding.ItemPendenciaBinding;
import com.vvm.sh.databinding.ItemUploadBinding;

public class PendenciaViewHolder extends RecyclerView.ViewHolder {

    public ItemPendenciaBinding binding;


    public PendenciaViewHolder(@NonNull View itemView) {
        super(itemView);

        binding = DataBindingUtil.bind(itemView);
    }
}
