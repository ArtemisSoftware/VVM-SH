package com.vvm.sh.ui.ocorrencias.adaptadores;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.vvm.sh.databinding.ItemOcorrenciaRegistoBinding;

public class OcorrenciaRegistoViewHolder extends RecyclerView.ViewHolder{

    public ItemOcorrenciaRegistoBinding binding;

    public OcorrenciaRegistoViewHolder(@NonNull View itemView) {
        super(itemView);
        binding = DataBindingUtil.bind(itemView);
    }
}
