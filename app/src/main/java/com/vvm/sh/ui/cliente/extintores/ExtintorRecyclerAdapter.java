package com.vvm.sh.ui.cliente.extintores;

import android.view.View;

import com.vvm.sh.R;
import com.vvm.sh.util.adaptadores.ItemRecyclerAdapter;
import com.vvm.sh.util.adaptadores.ItemViewHolder;
import com.vvm.sh.util.interfaces.OnItemListener;

public class ExtintorRecyclerAdapter extends ItemRecyclerAdapter {

    private OnItemListener onItemListener;


    public ExtintorRecyclerAdapter(OnItemListener onItemListener) {

        this.onItemListener = onItemListener;
    }

    @Override
    protected int obterLayout(int viewType) {
        return R.layout.extintor_list_card_item;
    }

    @Override
    protected ItemViewHolder obterViewHolder(View view, int viewType) {
        return new ExtintorViewHolder(view, onItemListener);
    }
}
