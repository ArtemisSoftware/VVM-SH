package com.vvm.sh.ui.atividadesPendentes;

import android.view.View;

import com.vvm.sh.R;
import com.vvm.sh.util.adaptadores.ItemRecyclerAdapter;
import com.vvm.sh.util.adaptadores.ItemViewHolder;
import com.vvm.sh.util.interfaces.OnCheckBoxItemListener;
import com.vvm.sh.util.interfaces.OnItemListener;

public class FormandoRecyclerAdapter extends ItemRecyclerAdapter {

    private OnItemListener onItemListener;
    private OnCheckBoxItemListener onCheckBoxItemListener;


    public FormandoRecyclerAdapter(OnItemListener onItemListener, OnCheckBoxItemListener onCheckBoxItemListener) {
        this.onItemListener = onItemListener;
        this.onCheckBoxItemListener = onCheckBoxItemListener;
    }

    @Override
    protected int obterLayout(int viewType) {
        return R.layout.item_formando;
    }

    @Override
    protected ItemViewHolder obterViewHolder(View view, int viewType) {
        return new FormandoViewHolder(view, this.onItemListener, this.onCheckBoxItemListener);
    }
}
