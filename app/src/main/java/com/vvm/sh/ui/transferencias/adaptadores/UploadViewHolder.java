package com.vvm.sh.ui.transferencias.adaptadores;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.vvm.sh.databinding.ItemUploadBinding;

public class UploadViewHolder extends RecyclerView.ViewHolder {

    public ItemUploadBinding binding;


    public UploadViewHolder(@NonNull View itemView) {
        super(itemView);

        binding = DataBindingUtil.bind(itemView);
    }
}
