package com.vvm.sh.ui.agenda.adaptadores;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.vvm.sh.databinding.ItemOpcaoClienteBinding;
import com.vvm.sh.util.adaptadores.Item;
import com.vvm.sh.util.adaptadores.ItemViewHolder;
import com.vvm.sh.util.interfaces.OnItemListener;

public class OpcaoClienteViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    ItemOpcaoClienteBinding binding;
    private OnItemListener onItemListener;

    public OpcaoClienteViewHolder(@NonNull View itemView, OnItemListener onItemListener) {
        super(itemView);
        binding = DataBindingUtil.bind(itemView);

        this.onItemListener = onItemListener;
        itemView.setOnClickListener(this);
    }



    @Override
    public void onClick(View v) {
        onItemListener.onItemClick(getAdapterPosition());
    }
}