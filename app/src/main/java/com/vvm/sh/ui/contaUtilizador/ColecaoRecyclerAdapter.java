package com.vvm.sh.ui.contaUtilizador;

import android.view.View;

import com.vvm.sh.R;
import com.vvm.sh.util.adaptadores.ItemRecyclerAdapter;
import com.vvm.sh.util.adaptadores.ItemViewHolder;

public class ColecaoRecyclerAdapter extends ItemRecyclerAdapter {



    @Override
    protected int obterLayout(int viewType) {
        return R.layout.item_tipo;
    }

    @Override
    protected ItemViewHolder obterViewHolder(View view, int viewType) {
        return new ColecaoViewHolder(view);
    }
}
