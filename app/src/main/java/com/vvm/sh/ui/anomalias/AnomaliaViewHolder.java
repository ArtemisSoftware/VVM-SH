package com.vvm.sh.ui.anomalias;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.vvm.sh.R;
import com.vvm.sh.util.adaptadores.Item;
import com.vvm.sh.util.adaptadores.ItemViewHolder;

import butterknife.BindView;

public class AnomaliaViewHolder extends ItemViewHolder {

    @BindView(R.id.txt_data)
    TextView txt_data;

    @BindView(R.id.txt_contacto)
    TextView txt_contacto;

    @BindView(R.id.txt_tipo)
    TextView txt_tipo;

    @BindView(R.id.txt_observacao)
    TextView txt_observacao;

    public AnomaliaViewHolder(@NonNull View itemView) {
        super(itemView);
    }

    @Override
    protected void preencherCampos(Item item) {

        Anomalia registo = (Anomalia) item;

        txt_data.setText(registo.obterData());
        txt_contacto.setText(registo.obterContacto());
        txt_tipo.setText(registo.obterTipo());
        txt_observacao.setText(registo.obterObservacao());
    }
}
