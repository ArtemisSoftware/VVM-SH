package com.vvm.sh.ui.pesquisa.adaptadores;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.vvm.sh.databinding.ItemPesquisaBinding;

public class PesquisaViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public ItemPesquisaBinding binding;
    private OnPesquisaListener.OnPesquisaSelecionadoListener onItemSelecionadoListener;
    private OnPesquisaListener.OnPesquisaRegistoListener onItemRegistoListener;

    public PesquisaViewHolder(@NonNull View itemView, OnPesquisaListener.OnPesquisaSelecionadoListener onItemListener) {
        super(itemView);
        binding = DataBindingUtil.bind(itemView);

        this.onItemSelecionadoListener = onItemListener;
        itemView.setOnClickListener(this);
    }


    public PesquisaViewHolder(@NonNull View itemView, OnPesquisaListener.OnPesquisaRegistoListener onItemListener) {
        super(itemView);
        binding = DataBindingUtil.bind(itemView);

        this.onItemRegistoListener = onItemListener;
        itemView.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {

        if(onItemSelecionadoListener != null){
            onItemSelecionadoListener.OnRemoverSelecao(binding.getTipo());
        }

        if(onItemRegistoListener != null){
            onItemRegistoListener.OnSelecionarClick(binding.getTipo());
        }

    }
}