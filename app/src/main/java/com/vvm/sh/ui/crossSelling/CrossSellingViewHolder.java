package com.vvm.sh.ui.crossSelling;

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

import butterknife.BindView;

public class CrossSellingViewHolder extends ItemViewHolder implements CheckBox.OnCheckedChangeListener {


    @BindView(R.id.chk_box_produto)
    CheckBox chk_box_produto;

    @BindView(R.id.rlt_lyt_sinaletica)
    RelativeLayout rlt_lyt_sinaletica;

    @BindView(R.id.txt_dimensao)
    TextView txt_dimensao;

    @BindView(R.id.txt_tipo)
    TextView txt_tipo;

    private OnCheckBoxItemListener onItemListener;


    public CrossSellingViewHolder(@NonNull View itemView, OnCheckBoxItemListener onItemListener) {
        super(itemView);

        this.onItemListener = onItemListener;
    }


    @Override
    protected void preencherCampos(Item item) {

        chk_box_produto.setText(item.obterDescricao());

        CrossSelling registo = (CrossSelling) item;

        chk_box_produto.setChecked(registo.obterSelecao());

        if(registo.existeSinaletica() == true){
            rlt_lyt_sinaletica.setVisibility(View.VISIBLE);
            txt_dimensao.setText(registo.obterDimensao());
            txt_tipo.setText(registo.obterTipo());
        }
        else{
            rlt_lyt_sinaletica.setVisibility(View.GONE);
        }


        chk_box_produto.setOnCheckedChangeListener(this);
    }


    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        onItemListener.onItemChecked(getAdapterPosition(), isChecked);
    }
}
