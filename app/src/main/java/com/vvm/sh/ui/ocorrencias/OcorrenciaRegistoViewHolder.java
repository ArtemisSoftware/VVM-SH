package com.vvm.sh.ui.ocorrencias;

import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import androidx.annotation.NonNull;

import com.vvm.sh.R;
import com.vvm.sh.ui.ocorrencias.modelos.Ocorrencia;
import com.vvm.sh.util.adaptadores.Item;
import com.vvm.sh.util.adaptadores.ItemViewHolder;
import com.vvm.sh.util.interfaces.OnCheckBoxItemListener;

import butterknife.BindView;

public class OcorrenciaRegistoViewHolder extends ItemViewHolder implements CheckBox.OnCheckedChangeListener {


    @BindView(R.id.chk_box_id)
    CheckBox chk_box_id;


    private OnCheckBoxItemListener onItemListener;


    public OcorrenciaRegistoViewHolder(@NonNull View itemView, OnCheckBoxItemListener onItemListener) {
        super(itemView);

        this.onItemListener = onItemListener;
    }


    @Override
    protected void preencherCampos(Item item) {

//        chk_box_id.setText(item.obterDescricao());
//
//        Ocorrencia registo = (Ocorrencia) item;
//
//        chk_box_id.setChecked(registo.obterSelecao());
//        chk_box_id.setOnCheckedChangeListener(this);
    }


    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        onItemListener.onItemChecked(getAdapterPosition(), isChecked);
    }
}
