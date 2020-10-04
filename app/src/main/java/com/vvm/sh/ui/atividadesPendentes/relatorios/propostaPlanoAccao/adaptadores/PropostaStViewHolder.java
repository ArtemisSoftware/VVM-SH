package com.vvm.sh.ui.atividadesPendentes.relatorios.propostaPlanoAccao.adaptadores;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.vvm.sh.databinding.ItemPropostaCondicoesStBinding;

public class PropostaStViewHolder extends RecyclerView.ViewHolder {


    public ItemPropostaCondicoesStBinding binding;


    public PropostaStViewHolder(@NonNull View itemView) {
        super(itemView);

        binding = DataBindingUtil.bind(itemView);
    }

}
