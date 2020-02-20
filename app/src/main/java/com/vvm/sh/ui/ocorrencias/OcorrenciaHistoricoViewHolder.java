package com.vvm.sh.ui.ocorrencias;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.vvm.sh.R;
import com.vvm.sh.util.adaptadores.Item;
import com.vvm.sh.util.adaptadores.ItemViewHolder;

import butterknife.BindView;

public class OcorrenciaHistoricoViewHolder extends ItemViewHolder {

    @BindView(R.id.txt_data)
    TextView txt_data;

    @BindView(R.id.txt_estado)
    TextView txt_estado;

    @BindView(R.id.txt_departamento)
    TextView txt_departamento;

    @BindView(R.id.txt_observacao)
    TextView txt_observacao;


    public OcorrenciaHistoricoViewHolder(@NonNull View itemView) {
        super(itemView);
    }

    @Override
    protected void preencherCampos(Item item) {

        Ocorrencia registo = (Ocorrencia) item;

        txt_data.setText(registo.obterData());
        txt_estado.setText(registo.obterSituacao());
        txt_departamento.setText(registo.obterDepartamento());
        txt_observacao.setText(registo.obterObservacao());
    }
}
