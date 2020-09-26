package com.vvm.sh.ui.atividadesPendentes.relatorios.trabalhadoresVulneraveis.adaptadores;

import android.view.View;
import android.widget.CompoundButton;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.vvm.sh.databinding.ItemTrabalhadorVulneravelBinding;

public class TrabalhadorVulneravelViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {


    public ItemTrabalhadorVulneravelBinding binding;

    private OnTrabalhadorVulneravelListener listener;


    public TrabalhadorVulneravelViewHolder(@NonNull View itemView, OnTrabalhadorVulneravelListener listener) {
        super(itemView);

        binding = DataBindingUtil.bind(itemView);

        this.listener = listener;
        itemView.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        this.listener.OnTrabalhadorVulneravelClick(binding.getVulnerabilidade());
    }
}
