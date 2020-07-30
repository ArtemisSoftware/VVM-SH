package com.vvm.sh.ui.anomalias.adaptadores;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.vvm.sh.databinding.ItemAnomaliaBinding;
import com.vvm.sh.databinding.ItemAnomaliaRegistadaBinding;

public class AnomaliaRegistadaViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public ItemAnomaliaRegistadaBinding binding;

    public AnomaliaRegistadaViewHolder(@NonNull View itemView) {
        super(itemView);

        binding = DataBindingUtil.bind(itemView);
        //this.listener = listener;
        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        //this.listener.onItemClick(binding.getAnomalia().id);
    }
}
