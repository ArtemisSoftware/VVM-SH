package com.vvm.sh.ui.atividadesExecutadas;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.vvm.sh.R;
import com.vvm.sh.util.adaptadores.Item;
import com.vvm.sh.util.adaptadores.ItemViewHolder;

import butterknife.BindView;

public class AtividadeExecutadaViewHolder extends ItemViewHolder {


    @BindView(R.id.txt_data)
    TextView txt_data;


    public AtividadeExecutadaViewHolder(@NonNull View itemView) {
        super(itemView);
    }

    @Override
    protected void preencherCampos(Item item) {
        txt_data.setText(((AtividadeExecutada) item).obterData());
    }
}
