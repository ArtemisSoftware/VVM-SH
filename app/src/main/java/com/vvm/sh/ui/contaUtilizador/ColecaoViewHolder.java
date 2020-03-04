package com.vvm.sh.ui.contaUtilizador;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.vvm.sh.R;
import com.vvm.sh.util.adaptadores.Item;
import com.vvm.sh.util.adaptadores.ItemViewHolder;
import com.vvm.sh.util.interfaces.OnItemListener;

import butterknife.BindView;

public class ColecaoViewHolder extends ItemViewHolder  {

    @BindView(R.id.txt_numero_registos)
    TextView txt_numero_registos;

    @BindView(R.id.txt_data)
    TextView txt_data;



    public ColecaoViewHolder(@NonNull View itemView) {
        super(itemView);

    }

    @Override
    protected void preencherCampos(Item item) {

        Colecao registo = (Colecao) item;

        txt_data.setText(registo.obterData());
        txt_numero_registos.setText(registo.obterNumeroRegistos() + "");
    }




}
