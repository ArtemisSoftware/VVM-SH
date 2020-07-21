package com.vvm.sh.ui.agenda;

import android.view.View;

import com.vvm.sh.R;
import com.vvm.sh.util.adaptadores.ItemRecyclerAdapter;
import com.vvm.sh.util.adaptadores.ItemViewHolder;
import com.vvm.sh.util.interfaces.OnItemListener;
import com.vvm.sh.util.interfaces.OnItemLongListener;

public class TarefaRecyclerAdapter extends ItemRecyclerAdapter {


    private OnItemListener onItemListener;
    private OnItemLongListener onItemLongListener;


    public TarefaRecyclerAdapter(OnItemListener onItemListener, OnItemLongListener onItemLongListener) {

        this.onItemListener = onItemListener;
        this.onItemLongListener = onItemLongListener;
    }


    @Override
    protected int obterLayout(int viewType) {
        return R.layout.item_tarefa;
    }

    @Override
    protected ItemViewHolder obterViewHolder(View view, int viewType) {
        return new TarefaViewHolder(view, this.onItemListener, this.onItemLongListener);
    }


}
