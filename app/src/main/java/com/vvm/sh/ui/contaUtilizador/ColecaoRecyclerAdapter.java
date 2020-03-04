package com.vvm.sh.ui.contaUtilizador;

import android.view.View;

import com.vvm.sh.R;
import com.vvm.sh.util.adaptadores.ItemRecyclerAdapter;
import com.vvm.sh.util.adaptadores.ItemViewHolder;
import com.vvm.sh.util.interfaces.OnItemListener;

public class ColecaoRecyclerAdapter extends ItemRecyclerAdapter {

    private OnItemListener onItemListener;


    public ColecaoRecyclerAdapter(OnItemListener onItemListener) {
        this.onItemListener = onItemListener;
    }

    @Override
    protected int obterLayout(int viewType) {
        return R.layout.tipo_list_card_item;
    }

    @Override
    protected ItemViewHolder obterViewHolder(View view, int viewType) {
        return new ColecaoViewHolder(view, this.onItemListener);
    }
}
