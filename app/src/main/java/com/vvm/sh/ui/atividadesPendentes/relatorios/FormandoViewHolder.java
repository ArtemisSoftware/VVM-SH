package com.vvm.sh.ui.atividadesPendentes.relatorios;

import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.vvm.sh.databinding.ItemFormandoBinding;

public class FormandoViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, CheckBox.OnCheckedChangeListener {


    public ItemFormandoBinding binding;

    private OnFormacaoListener listener;


    public FormandoViewHolder(@NonNull View itemView, OnFormacaoListener listener) {
        super(itemView);

        binding = DataBindingUtil.bind(itemView);

        this.listener = listener;
        itemView.setOnClickListener(this);
        binding.chkSelecionado.setOnCheckedChangeListener(this);
    }


    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

        if(binding.getFormando().resultado.selecionado != isChecked) {
            this.listener.OnSelecionadoCheck(binding.getFormando(), isChecked);
        }
    }


    @Override
    public void onClick(View v) {
        this.listener.OnFormandoClick(binding.getFormando());
    }
}
