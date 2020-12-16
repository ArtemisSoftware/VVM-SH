package com.vvm.sh.ui.anomalias.adaptadores;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.vvm.sh.databinding.ItemAnomaliaBinding;

public class AnomaliaViewHolder extends RecyclerView.ViewHolder {

    public ItemAnomaliaBinding binding;

    public AnomaliaViewHolder(@NonNull View itemView) {
        super(itemView);

        binding = DataBindingUtil.bind(itemView);
    }

}
