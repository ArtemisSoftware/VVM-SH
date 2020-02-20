package com.vvm.sh.ui.agenda;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.vvm.sh.R;
import com.vvm.sh.util.adaptadores.Item;
import com.vvm.sh.util.adaptadores.ItemViewHolder;
import com.vvm.sh.util.interfaces.OnItemListener;

import butterknife.BindView;

public class OpcaoClienteViewHolder extends ItemViewHolder implements View.OnClickListener{


    private OnItemListener onItemListener;

    public OpcaoClienteViewHolder(@NonNull View itemView, OnItemListener onItemListener) {
        super(itemView);

        this.onItemListener = onItemListener;
        itemView.setOnClickListener(this);
    }

    @Override
    protected void preencherCampos(Item item) {

    }

    @Override
    public void onClick(View v) {
        onItemListener.onItemClick(getAdapterPosition());
    }
}
