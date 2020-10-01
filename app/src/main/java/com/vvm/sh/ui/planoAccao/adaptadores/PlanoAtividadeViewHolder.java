package com.vvm.sh.ui.planoAccao.adaptadores;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.vvm.sh.databinding.ItemPlanoAcaoNotaBinding;
import com.vvm.sh.databinding.ItemPlanoAcaoProgramacaoBinding;
import com.vvm.sh.util.constantes.Identificadores;
import com.vvm.sh.util.metodos.DatasUtil;

import java.util.Date;

public class PlanoAtividadeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public ItemPlanoAcaoProgramacaoBinding binding;
    private OnPlanoAtividadeListener listener;

    public PlanoAtividadeViewHolder(@NonNull View itemView, OnPlanoAtividadeListener listener) {
        super(itemView);

        binding = DataBindingUtil.bind(itemView);
        this.listener = listener;
        itemView.setOnClickListener(this);


        Date data = DatasUtil.obterDataAtual_Date();


        TextView txt [] = {
                binding.txt1, binding.txt2, binding.txt3, binding.txt4, binding.txt5, binding.txt6,
                binding.txt7, binding.txt8, binding.txt9, binding.txt10, binding.txt11, binding.txt12
        };

        for(int index = 0; index < 12; ++index){

            if(index >= data.getMonth()){
                txt[index].setOnClickListener(txt_mes_onClick);
            }


        }

//        binding.txt1.setOnClickListener(txt_mes_onClick);
//        binding.txt2.setOnClickListener(txt_mes_onClick);
//        binding.txt3.setOnClickListener(txt_mes_onClick);
//        binding.txt4.setOnClickListener(txt_mes_onClick);
//        binding.txt5.setOnClickListener(txt_mes_onClick);
//        binding.txt6.setOnClickListener(txt_mes_onClick);
//
//        binding.txt7.setOnClickListener(txt_mes_onClick);
//        binding.txt8.setOnClickListener(txt_mes_onClick);
//        binding.txt9.setOnClickListener(txt_mes_onClick);
//        binding.txt10.setOnClickListener(txt_mes_onClick);
//        binding.txt11.setOnClickListener(txt_mes_onClick);
//        binding.txt12.setOnClickListener(txt_mes_onClick);
    }


    @Override
    public void onClick(View v) {

        this.listener.OnAtividadeClick(binding.getAtividade());

    }


    View.OnClickListener txt_mes_onClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {


            if(binding.getAtividade().reprogramavel == true){
                listener.OnDataClick(binding.getAtividade(), ((Integer)v.getTag()));
            }
        }
    };

}