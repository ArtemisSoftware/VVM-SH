package com.vvm.sh.ui.atividadesExecutadas;

import android.view.View;

import com.vvm.sh.R;
import com.vvm.sh.util.adaptadores.ItemRecyclerAdapter;
import com.vvm.sh.util.adaptadores.ItemViewHolder;

public class AtividadeExecutadaRecyclerAdapter extends ItemRecyclerAdapter {

    @Override
    protected int obterLayout(int viewType) {
        return R.layout.item_atividade_executada;
    }

    @Override
    protected ItemViewHolder obterViewHolder(View view, int viewType) {
        return new AtividadeExecutadaViewHolder(view);
    }
}
