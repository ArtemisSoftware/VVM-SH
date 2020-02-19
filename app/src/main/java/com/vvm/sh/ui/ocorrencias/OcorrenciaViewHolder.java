package com.vvm.sh.ui.ocorrencias;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.vvm.sh.R;
import com.vvm.sh.ui.atividadesExecutadas.AtividadeExecutada;
import com.vvm.sh.util.adaptadores.Item;
import com.vvm.sh.util.adaptadores.ItemViewHolder;
import com.vvm.sh.util.interfaces.OnItemListener;

import butterknife.BindView;

public class OcorrenciaViewHolder extends ItemViewHolder implements View.OnClickListener {

    @BindView(R.id.txt_data_entrada)
    TextView txt_data_entrada;

    @BindView(R.id.txt_departamento)
    TextView txt_departamento;

    @BindView(R.id.txt_contrato)
    TextView txt_contrato;

    @BindView(R.id.txt_data)
    TextView txt_estado;

    private OnItemListener onItemListener;

    public OcorrenciaViewHolder(@NonNull View itemView) {
        super(itemView);

        this.onItemListener = onItemListener;
        itemView.setOnClickListener(this);
    }

    @Override
    protected void preencherCampos(Item item) {

        Ocorrencia registo = (Ocorrencia) item;

        txt_data_entrada.setText(registo.obterDataEntrada());
        txt_departamento.setText(registo.obterDepartamento());
        txt_contrato.setText(registo.obterContrato());
        txt_estado.setText(registo.obterFiscalizacao());
    }

    @Override
    public void onClick(View v) {
        onItemListener.onItemClick(getAdapterPosition());
    }
}
