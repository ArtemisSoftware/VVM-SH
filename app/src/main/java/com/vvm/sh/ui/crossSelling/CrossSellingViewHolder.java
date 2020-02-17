package com.vvm.sh.ui.crossSelling;

import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.vvm.sh.R;
import com.vvm.sh.util.adaptadores.Item;
import com.vvm.sh.util.adaptadores.ItemViewHolder;

import butterknife.BindView;

public class CrossSellingViewHolder extends ItemViewHolder implements CheckBox.OnCheckedChangeListener {


    @BindView(R.id.chk_box_produto)
    CheckBox chk_box_produto;

    @BindView(R.id.lnr_lyt_sinaletica)
    LinearLayout lnr_lyt_sinaletica;

    @BindView(R.id.txt_dimensao)
    TextView txt_dimensao;

    @BindView(R.id.txt_tipo)
    TextView txt_tipo;


    public CrossSellingViewHolder(@NonNull View itemView) {
        super(itemView);
    }

    @Override
    protected void preencherCampos(Item item) {

        chk_box_produto.setText(item.obterDescricao());
        chk_box_produto.setOnCheckedChangeListener(this);

        if(true){
            lnr_lyt_sinaletica.setVisibility(View.VISIBLE);
            txt_dimensao.setText("");
            txt_tipo.setText("");
        }
        else{
            lnr_lyt_sinaletica.setVisibility(View.GONE);
        }

    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

    }
}
