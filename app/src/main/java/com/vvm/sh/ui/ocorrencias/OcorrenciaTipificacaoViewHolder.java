package com.vvm.sh.ui.ocorrencias;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.vvm.sh.R;
import com.vvm.sh.util.adaptadores.Item;
import com.vvm.sh.util.adaptadores.ItemViewHolder;
import com.vvm.sh.util.interfaces.OnItemListener;

import butterknife.BindView;

public class OcorrenciaTipificacaoViewHolder extends ItemViewHolder implements View.OnClickListener {

    @BindView(R.id.txt_codigo)
    TextView txt_codigo;

    private OnItemListener onItemListener;


    public OcorrenciaTipificacaoViewHolder(@NonNull View itemView, OnItemListener onItemListener) {
        super(itemView);

        this.onItemListener = onItemListener;
        itemView.setOnClickListener(this);
    }

    @Override
    protected void preencherCampos(Item item) {

        Ocorrencia registo = (Ocorrencia) item;

        txt_codigo.setText(registo.obterCodigo());
    }

    @Override
    public void onClick(View v) {
        onItemListener.onItemClick(getAdapterPosition());
    }
}
