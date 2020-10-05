package com.vvm.sh.ui.registoVisita.adaptadores;

import android.view.View;
import android.widget.CheckBox;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.vvm.sh.databinding.ItemTrabalhoRealizadoBinding;

public class TrabalhoRealizadoViewHolder extends RecyclerView.ViewHolder implements CheckBox.OnClickListener {


    ItemTrabalhoRealizadoBinding binding;
    private OnRegistoVisitaListener onItemListener;


    public TrabalhoRealizadoViewHolder(View itemView, OnRegistoVisitaListener onItemListener) {
        super(itemView);
        binding = DataBindingUtil.bind(itemView);

        this.onItemListener = onItemListener;
        binding.chkBoxItem.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
       onItemListener.onItemChecked(binding.getTrabalho().tipo.id, ((CheckBox) v).isChecked());
    }
}
