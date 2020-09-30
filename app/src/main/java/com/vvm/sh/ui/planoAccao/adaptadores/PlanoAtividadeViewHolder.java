package com.vvm.sh.ui.planoAccao.adaptadores;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.vvm.sh.databinding.ItemPlanoAcaoNotaBinding;
import com.vvm.sh.databinding.ItemPlanoAcaoProgramacaoBinding;
import com.vvm.sh.util.constantes.Identificadores;

public class PlanoAtividadeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public ItemPlanoAcaoProgramacaoBinding binding;
    private OnPlanoAtividadeListener listener;

    public PlanoAtividadeViewHolder(@NonNull View itemView, OnPlanoAtividadeListener listener) {
        super(itemView);

        binding = DataBindingUtil.bind(itemView);
        this.listener = listener;
        itemView.setOnClickListener(this);

        binding.txt1.setOnClickListener(txt_mes_onClick);

    }


    @Override
    public void onClick(View v) {

        this.listener.OnAtividadeClick(binding.getAtividade());

    }


    View.OnClickListener txt_mes_onClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {


            if(binding.getAtividade().reprogramavel == true){
                listener.OnDataClick(binding.getAtividade(), Integer.parseInt(v.getTag().toString()));
            }
        }
    };

}