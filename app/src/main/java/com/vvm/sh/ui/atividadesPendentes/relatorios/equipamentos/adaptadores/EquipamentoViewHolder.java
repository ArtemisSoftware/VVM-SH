package com.vvm.sh.ui.atividadesPendentes.relatorios.equipamentos.adaptadores;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.vvm.sh.databinding.ItemPesquisaEquipamentoBinding;
import com.vvm.sh.ui.pesquisa.OnPesquisaListener;

public class EquipamentoViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public ItemPesquisaEquipamentoBinding binding;
    private OnPesquisaListener.OnPesquisaEquipamentoSelecionadoListener onItemSelecionadoListener;
    private OnPesquisaListener.OnPesquisaEquipamentoRegistoListener onItemRegistoListener;

    public EquipamentoViewHolder(@NonNull View itemView, OnPesquisaListener.OnPesquisaEquipamentoSelecionadoListener listener) {
        super(itemView);
        binding = DataBindingUtil.bind(itemView);

        this.onItemSelecionadoListener = listener;
        itemView.setOnClickListener(this);
    }


    public EquipamentoViewHolder(@NonNull View itemView, OnPesquisaListener.OnPesquisaEquipamentoRegistoListener onItemListener) {
        super(itemView);
        binding = DataBindingUtil.bind(itemView);

        this.onItemRegistoListener = onItemListener;
        itemView.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {

        if(onItemSelecionadoListener != null){
            onItemSelecionadoListener.OnRemoverSelecao(binding.getEquipamento());
        }

        if(onItemRegistoListener != null){
            onItemRegistoListener.OnSelecionarClick(binding.getEquipamento());
        }

    }
}