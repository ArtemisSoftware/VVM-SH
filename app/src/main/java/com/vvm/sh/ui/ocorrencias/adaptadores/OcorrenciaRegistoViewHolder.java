package com.vvm.sh.ui.ocorrencias.adaptadores;

import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.vvm.sh.databinding.ItemOcorrenciaRegistoBinding;

public class OcorrenciaRegistoViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, CheckBox.OnCheckedChangeListener{


    public ItemOcorrenciaRegistoBinding binding;

    private OnOcorrenciaRegistoListener listener;


    public OcorrenciaRegistoViewHolder(@NonNull View itemView, OnOcorrenciaRegistoListener listener) {
        super(itemView);
        binding = DataBindingUtil.bind(itemView);

        this.listener = listener;
        itemView.setOnClickListener(this);


        binding.checkBox.setOnCheckedChangeListener(this);
    }



    @Override
    public void onClick(View v) {
        listener.OnOcorrenciaClick(binding.getTipo());
    }


    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        listener.OnOcorrenciaCheck(binding.getTipo(), isChecked);
    }
}
