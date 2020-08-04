package com.vvm.sh.ui.ocorrencias.adaptadores;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.vvm.sh.databinding.ItemOcorrenciaHistoricoBinding;

public class OcorrenciaHistoricoViewHolder extends RecyclerView.ViewHolder {


    public ItemOcorrenciaHistoricoBinding binding;

    public OcorrenciaHistoricoViewHolder(@NonNull View itemView) {
        super(itemView);

        binding = DataBindingUtil.bind(itemView);
    }

}
