package com.vvm.sh.ui.atividadesPendentes.relatorios.checklist.adaptadores;

import android.view.View;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.vvm.sh.R;
import com.vvm.sh.databinding.ItemChecklistFotoBinding;
import com.vvm.sh.databinding.ItemChecklistUtsBinding;

public class FotoViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {



    public ItemChecklistFotoBinding binding;
    private OnChecklistListener.OnQuestaoListener onItemListener;


    public FotoViewHolder(View itemView, OnChecklistListener.OnQuestaoListener onItemListener) {
        super(itemView);
        binding = DataBindingUtil.bind(itemView);

        this.onItemListener = onItemListener;
        binding.crlBtnGaleria.setOnClickListener(this);
        binding.crlBtnRegistar.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {

        if(v.getId() == R.id.crl_btn_galeria) {
            onItemListener.OnGaleriaClick(binding.getQuestao());
        }
        else{
            onItemListener.OnRegistarFoto(binding.getQuestao());
        }
    }
}