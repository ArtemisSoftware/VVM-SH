package com.vvm.sh.ui.atividadesPendentes;

import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.vvm.sh.R;
import com.vvm.sh.util.adaptadores.Item;
import com.vvm.sh.util.adaptadores.ItemViewHolder;
import com.vvm.sh.util.interfaces.OnCheckBoxItemListener;
import com.vvm.sh.util.interfaces.OnItemListener;

import butterknife.BindView;

public class FormandoViewHolder extends ItemViewHolder implements View.OnClickListener, CheckBox.OnCheckedChangeListener {


    @BindView(R.id.chk_selecionado)
    CheckBox chk_selecionado;


    @BindView(R.id.txt_identificacao)
    TextView txt_identificacao;


    private OnItemListener onItemListener;
    private OnCheckBoxItemListener onCheckBoxItemListener;


    public FormandoViewHolder(@NonNull View itemView, OnItemListener onItemListener, OnCheckBoxItemListener onCheckBoxItemListener) {
        super(itemView);

        this.onItemListener = onItemListener;
        itemView.setOnClickListener(this);
        this.onCheckBoxItemListener = onCheckBoxItemListener;
    }


    @Override
    protected void preencherCampos(Item item) {

        Formando registo = (Formando) item;

        chk_selecionado.setChecked(registo.obterSelecao());
        txt_identificacao.setText(registo.obterBiCartaoCidadao());

        chk_selecionado.setOnCheckedChangeListener(this);
    }


    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        onCheckBoxItemListener.onItemChecked(getAdapterPosition(), isChecked);
    }


    @Override
    public void onClick(View v) {
        onItemListener.onItemClick(getAdapterPosition());
    }
}
