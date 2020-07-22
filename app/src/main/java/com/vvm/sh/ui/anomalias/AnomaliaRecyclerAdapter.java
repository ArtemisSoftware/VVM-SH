package com.vvm.sh.ui.anomalias;

import android.view.View;

import com.vvm.sh.R;
import com.vvm.sh.util.adaptadores.ItemRecyclerAdapter;
import com.vvm.sh.util.adaptadores.ItemViewHolder;

public class AnomaliaRecyclerAdapter extends ItemRecyclerAdapter {
    @Override
    protected int obterLayout(int viewType) {
        return R.layout.item_anomalia;
    }

    @Override
    protected ItemViewHolder obterViewHolder(View view, int viewType) {
        return null;//new AnomaliaViewHolder(view);
    }



    /*
    Anomalia registo = (Anomalia) item;

        txt_observacao.setText(registo.obterObservacao());

        if(registo.obterTipoAnomalia() == Anomalia.TIPO_ANOMALIA) {
            txt_data.setText(registo.obterData());
            txt_contacto.setText(registo.obterContacto());
            txt_tipo.setText(registo.obterTipo());
        }
        else{
            lnr_lyt_data.setVisibility(View.GONE);
            lnr_lyt_contacto.setVisibility(View.GONE);
            lnr_lyt_tipo.setVisibility(View.GONE);
        }
    */
}
