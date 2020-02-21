package com.vvm.sh.ui.cliente.extintores;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.vvm.sh.R;
import com.vvm.sh.util.adaptadores.Item;
import com.vvm.sh.util.adaptadores.ItemViewHolder;
import com.vvm.sh.util.interfaces.OnItemListener;

import butterknife.BindView;

public class ExtintorViewHolder extends ItemViewHolder implements View.OnClickListener{


    @BindView(R.id.img_valido)
    ImageView img_valido;

    @BindView(R.id.img_data_validade)
    ImageView img_data_validade;

    @BindView(R.id.txt_data_validade)
    TextView txt_data_validade;

    @BindView(R.id.txt_morada)
    TextView txt_morada;

    @BindView(R.id.txt_quantidade)
    TextView txt_quantidade;

    private OnItemListener onItemListener;


    public ExtintorViewHolder(@NonNull View itemView, OnItemListener onItemListener) {
        super(itemView);

        this.onItemListener = onItemListener;
        itemView.setOnClickListener(this);
    }

    @Override
    protected void preencherCampos(Item item) {

        Extintor registo = (Extintor) item;

        txt_morada.setText(registo.obterEndereco());
        txt_quantidade.setText(registo.obterQuantidade());
        txt_data_validade.setText(registo.obterDataValidade());

        if(registo.obterEstadoNovaDataValidade() == true){
            img_data_validade.setVisibility(View.VISIBLE);
        }
        else{
            img_data_validade.setVisibility(View.GONE);
        }

        //TODO: a imagem deve mudar consoante a validade. Calendario cinzento ou colorido

        /*
        if(registo.obterValidade() == true){
            img_view_valido.setVisibility(View.VISIBLE);
        }
        else{
            img_view_valido.setVisibility(View.INVISIBLE);
        }
        */
    }

    @Override
    public void onClick(View v) {
        onItemListener.onItemClick(getAdapterPosition());
    }
}
