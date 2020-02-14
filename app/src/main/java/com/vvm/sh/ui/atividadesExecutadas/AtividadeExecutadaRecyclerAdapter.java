package com.vvm.sh.ui.atividadesExecutadas;

import android.view.View;

import com.vvm.sh.R;
import com.vvm.sh.util.adaptadores.ItemRecyclerAdapter;
import com.vvm.sh.util.adaptadores.ItemViewHolder;

public class AtividadeExecutadaRecyclerAdapter extends ItemRecyclerAdapter {

    @Override
    protected int obterLayout() {
        return R.layout.atividade_executada_list_item;
    }

    @Override
    protected ItemViewHolder obterViewHolder(View view) {
        return new AtividadeExecutadaViewHolder(view);
    }
}
