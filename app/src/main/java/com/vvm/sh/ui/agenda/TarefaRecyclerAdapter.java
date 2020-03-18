package com.vvm.sh.ui.agenda;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.vvm.sh.R;
import com.vvm.sh.util.adaptadores.ItemRecyclerAdapter;
import com.vvm.sh.util.adaptadores.ItemViewHolder;
import com.vvm.sh.util.interfaces.OnItemListener;
import com.vvm.sh.util.interfaces.OnItemLongListener;

import java.util.ArrayList;
import java.util.List;

public class TarefaRecyclerAdapter extends ItemRecyclerAdapter {


    private OnItemListener onItemListener;
    private OnItemLongListener onItemLongListener;


    public TarefaRecyclerAdapter(OnItemListener onItemListener, OnItemLongListener onItemLongListener) {

        this.onItemListener = onItemListener;
        this.onItemLongListener = onItemLongListener;
    }


    @Override
    protected int obterLayout(int viewType) {
        return R.layout.agenda_list_card_item;
    }

    @Override
    protected ItemViewHolder obterViewHolder(View view, int viewType) {
        return new TarefaViewHolder(view, this.onItemListener, this.onItemLongListener);
    }


}
