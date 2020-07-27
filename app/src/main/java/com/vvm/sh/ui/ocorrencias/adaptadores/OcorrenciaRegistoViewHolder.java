package com.vvm.sh.ui.ocorrencias.adaptadores;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.vvm.sh.databinding.ItemOcorrenciaRegistoBinding;

public class OcorrenciaRegistoViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{


    public ItemOcorrenciaRegistoBinding binding;

    private OnOcorrenciaRegistoListener listener;


    public OcorrenciaRegistoViewHolder(@NonNull View itemView, OnOcorrenciaRegistoListener listener) {
        super(itemView);
        binding = DataBindingUtil.bind(itemView);

        this.listener = listener;
        itemView.setOnClickListener(this);
    }



    @Override
    public void onClick(View v) {
        listener.OnOcorrenciaClick(binding.getTipo());
    }


}
