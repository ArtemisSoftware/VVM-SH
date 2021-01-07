package com.vvm.sh.ui.informacaoSst.adaptadores;

import android.view.View;
import android.widget.CheckBox;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.vvm.sh.databinding.ItemObrigacaoLegalBinding;
import com.vvm.sh.databinding.ItemTrabalhoRealizadoBinding;
import com.vvm.sh.ui.registoVisita.adaptadores.OnRegistoVisitaListener;

public class ObrigacaoLegalViewHolder extends RecyclerView.ViewHolder implements CheckBox.OnClickListener {


    ItemObrigacaoLegalBinding binding;
    private OnInformacaoSstListener onItemListener;


    public ObrigacaoLegalViewHolder(View itemView, OnInformacaoSstListener onItemListener) {
        super(itemView);
        binding = DataBindingUtil.bind(itemView);

        this.onItemListener = onItemListener;
        binding.chkBoxItem.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
       onItemListener.onItemChecked(binding.getObrigacao().tipo.id, ((CheckBox) v).isChecked());
    }
}
