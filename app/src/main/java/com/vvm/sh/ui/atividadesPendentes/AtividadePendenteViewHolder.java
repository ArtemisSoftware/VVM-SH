package com.vvm.sh.ui.atividadesPendentes;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.vvm.sh.R;
import com.vvm.sh.util.adaptadores.Item;
import com.vvm.sh.util.adaptadores.ItemViewHolder;
import com.vvm.sh.util.interfaces.OnItemListener;

import butterknife.BindView;

public class AtividadePendenteViewHolder extends ItemViewHolder implements View.OnClickListener{

    @BindView(R.id.txt_data)
    TextView txt_data;

    @BindView(R.id.txt_anuidade)
    TextView txt_anuidade;

    @BindView(R.id.lnr_lyt_relatorio)
    LinearLayout lnr_lyt_relatorio;

    @BindView(R.id.txt_relatorio)
    TextView txt_relatorio;

    private OnItemListener onItemListener;

    public AtividadePendenteViewHolder(@NonNull View itemView, OnItemListener onItemListener) {
        super(itemView);

        this.onItemListener = onItemListener;
        itemView.setOnClickListener(this);
    }

    @Override
    protected void preencherCampos(Item item) {

        AtividadePendente registo = (AtividadePendente) item;

        txt_data.setText(registo.obterData());
        txt_anuidade.setText(registo.obterAnuidade());
        txt_anuidade.setText(registo.obterAnuidade());

        if(registo.obterIdRelatorio() == AtividadePendente.SEM_RELATORIO){
            lnr_lyt_relatorio.setVisibility(View.GONE);
        }
        else{
            lnr_lyt_relatorio.setVisibility(View.VISIBLE);
        }

        txt_relatorio.setText(registo.obterRelatorio());

    }

    @Override
    public void onClick(View v) {
        onItemListener.onItemClick(getAdapterPosition());
    }


}
