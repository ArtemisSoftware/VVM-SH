package com.vvm.sh.ui.atividadesPendentes.relatorios.checklist.adaptadores;

import android.view.View;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.vvm.sh.databinding.ItemChecklistObservacaoBinding;

public class ObservacaoViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {



    public ItemChecklistObservacaoBinding binding;
    private OnChecklistListener.OnQuestaoListener onItemListener;


    public ObservacaoViewHolder(View itemView, OnChecklistListener.OnQuestaoListener onItemListener) {
        super(itemView);
        binding = DataBindingUtil.bind(itemView);

        this.onItemListener = onItemListener;
        binding.crlBtnCalendario.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        onItemListener.OnObservacaoClick(binding.getQuestao());
    }
}

