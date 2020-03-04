package com.vvm.sh.ui.contaUtilizador;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.vvm.sh.R;
import com.vvm.sh.util.adaptadores.Item;
import com.vvm.sh.util.adaptadores.ItemViewHolder;
import com.vvm.sh.util.interfaces.OnItemListener;

import butterknife.BindView;

public class ColecaoViewHolder extends ItemViewHolder implements View.OnClickListener {

    @BindView(R.id.txt_numero_registos)
    TextView txt_numero_registos;

    @BindView(R.id.txt_data)
    TextView txt_data;

    private OnItemListener onItemListener;


    public ColecaoViewHolder(@NonNull View itemView, OnItemListener onItemListener) {
        super(itemView);

        this.onItemListener = onItemListener;
        itemView.setOnClickListener(this);
    }

    @Override
    protected void preencherCampos(Item item) {

        Colecao registo = (Colecao) item;

        txt_data.setText(registo.obterData());
        txt_numero_registos.setText(registo.obterNumeroRegistos() + "");
    }


    @Override
    public void onClick(View v) {
        onItemListener.onItemClick(getAdapterPosition());
    }


}
