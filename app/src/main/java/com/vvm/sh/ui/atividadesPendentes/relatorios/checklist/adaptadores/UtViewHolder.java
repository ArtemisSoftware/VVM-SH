package com.vvm.sh.ui.atividadesPendentes.relatorios.checklist.adaptadores;

import android.view.View;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.vvm.sh.R;
import com.vvm.sh.databinding.ItemChecklistUtsBinding;

public class UtViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {



    public ItemChecklistUtsBinding binding;
    private OnChecklistListener.OnQuestaoListener onItemListener;


    public UtViewHolder(View itemView, OnChecklistListener.OnQuestaoListener onItemListener) {
        super(itemView);
        binding = DataBindingUtil.bind(itemView);

        this.onItemListener = onItemListener;
        binding.crlBtnPergunta1.setOnClickListener(this);
        binding.crlBtnPergunta2.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {

        if(v.getId() == R.id.crl_btn_pergunta_1) {
            onItemListener.OnUtClick(binding.getQuestao(), 1);
        }
        else{
            onItemListener.OnUtClick(binding.getQuestao(), 2);
        }
    }
}