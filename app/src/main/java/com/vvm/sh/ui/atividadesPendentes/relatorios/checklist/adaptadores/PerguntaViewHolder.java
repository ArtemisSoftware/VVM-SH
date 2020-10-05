package com.vvm.sh.ui.atividadesPendentes.relatorios.checklist.adaptadores;

import android.view.View;
import android.widget.CheckBox;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.vvm.sh.databinding.ItemChecklistPerguntaBinding;

public class PerguntaViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {



    public ItemChecklistPerguntaBinding binding;
    private OnChecklistListener.OnQuestaoListener onItemListener;


    public PerguntaViewHolder(View itemView, OnChecklistListener.OnQuestaoListener onItemListener) {
        super(itemView);
        binding = DataBindingUtil.bind(itemView);

        this.onItemListener = onItemListener;
        binding.crlBtnPergunta.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        onItemListener.OnPerguntaClick(binding.getQuestao());
    }
}


