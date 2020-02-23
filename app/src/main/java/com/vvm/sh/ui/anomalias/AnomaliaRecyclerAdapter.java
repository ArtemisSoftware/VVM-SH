package com.vvm.sh.ui.anomalias;

import android.view.View;

import com.vvm.sh.R;
import com.vvm.sh.util.adaptadores.ItemRecyclerAdapter;
import com.vvm.sh.util.adaptadores.ItemViewHolder;

public class AnomaliaRecyclerAdapter extends ItemRecyclerAdapter {
    @Override
    protected int obterLayout(int viewType) {
        return R.layout.anomalia_list_card_item;
    }

    @Override
    protected ItemViewHolder obterViewHolder(View view, int viewType) {
        return new AnomaliaViewHolder(view);
    }
}
