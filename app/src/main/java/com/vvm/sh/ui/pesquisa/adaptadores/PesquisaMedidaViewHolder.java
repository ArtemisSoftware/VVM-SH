package com.vvm.sh.ui.pesquisa.adaptadores;

import android.view.View;
import android.widget.CheckBox;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.vvm.sh.databinding.ItemPesquisaMedidaBinding;

public class PesquisaMedidaViewHolder extends RecyclerView.ViewHolder implements CheckBox.OnClickListener {


    ItemPesquisaMedidaBinding binding;
    private OnPesquisaListener.OnPesquisaMedidaListener onItemListener;


    public PesquisaMedidaViewHolder(View itemView, OnPesquisaListener.OnPesquisaMedidaListener onItemListener) {
        super(itemView);
        binding = DataBindingUtil.bind(itemView);

        this.onItemListener = onItemListener;
        binding.checkBox.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        binding.getMedida().selecionado = ((CheckBox) v).isChecked();
        onItemListener.OnSelecionarClick(binding.getMedida(), ((CheckBox) v).isChecked());
    }
}
