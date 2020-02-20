package com.vvm.sh.ui.ocorrencias;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.vvm.sh.R;
import com.vvm.sh.util.adaptadores.Item;
import com.vvm.sh.util.adaptadores.ItemViewHolder;

import butterknife.BindView;

public class OcorrenciaNovaViewHolder extends ItemViewHolder {


    @BindView(R.id.txt_fiscalizado)
    TextView txt_fiscalizado;

    @BindView(R.id.txt_dias)
    TextView txt_dias;

    @BindView(R.id.txt_observacao)
    TextView txt_observacao;


    public OcorrenciaNovaViewHolder(@NonNull View itemView) {
        super(itemView);
    }

    @Override
    protected void preencherCampos(Item item) {

        Ocorrencia registo = (Ocorrencia) item;

        txt_fiscalizado.setText(registo.obterFiscalizacao());
        txt_dias.setText(registo.obterDuracao());
        txt_observacao.setText(registo.obterObservacao());
    }
}
