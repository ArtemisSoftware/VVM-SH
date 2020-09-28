package com.vvm.sh.ui.atividadesPendentes.relatorios.propostaPlanoAccao.modelos;

import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.vvm.sh.databinding.ItemPlanoAccaoStBinding;

public class PropostaStViewHolder extends RecyclerView.ViewHolder {


    public ItemPlanoAccaoStBinding binding;


    public PropostaStViewHolder(@NonNull View itemView) {
        super(itemView);

        binding = DataBindingUtil.bind(itemView);
    }

}
