package com.vvm.sh.ui.cliente.extintores;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.vvm.sh.R;
import com.vvm.sh.databinding.ItemExtintorBinding;
import com.vvm.sh.util.adaptadores.Item;
import com.vvm.sh.util.adaptadores.ItemViewHolder;
import com.vvm.sh.util.interfaces.OnItemListener;

import butterknife.BindView;

public class ExtintorViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{


    public ItemExtintorBinding binding;

    //--private OnFormacaoListener listener;


    public ExtintorViewHolder(@NonNull View itemView, OnItemListener onItemListener) {
        super(itemView);

        binding = DataBindingUtil.bind(itemView);

        //--this.listener = listener;
        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        /*this.listener.OnExtintorClick(binding.getParqueExtintor());*/
    }
}
