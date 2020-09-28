package com.vvm.sh.ui.atividadesPendentes.relatorios.propostaPlanoAccao.modelos;

import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.vvm.sh.databinding.ItemPlanoAccaoMedidasAvaliacaoBinding;
import com.vvm.sh.ui.atividadesPendentes.relatorios.propostaPlanoAccao.adaptadores.OnPropostaPlanoAcaoListener;

public class PropostaMedidaViewHolder extends RecyclerView.ViewHolder implements CheckBox.OnCheckedChangeListener {


    public ItemPlanoAccaoMedidasAvaliacaoBinding binding;

    private OnPropostaPlanoAcaoListener listener;


    public PropostaMedidaViewHolder(@NonNull View itemView, OnPropostaPlanoAcaoListener listener) {
        super(itemView);

        binding = DataBindingUtil.bind(itemView);

        this.listener = listener;
        binding.chkBoxItem.setOnCheckedChangeListener(this);
    }


    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

        if(binding.getProposta().resultado.selecionado != isChecked) {
            this.listener.OnCheckProposta(binding.getProposta(), isChecked);
        }
    }

}