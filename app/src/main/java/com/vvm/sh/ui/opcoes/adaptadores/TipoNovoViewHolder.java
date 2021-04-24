package com.vvm.sh.ui.opcoes.adaptadores;

import android.view.View;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.vvm.sh.databinding.ItemTipoBinding;
import com.vvm.sh.databinding.ItemTipoNovoBinding;

public class TipoNovoViewHolder extends RecyclerView.ViewHolder{

    ItemTipoNovoBinding binding;


    public TipoNovoViewHolder(View itemView) {
        super(itemView);
        binding = DataBindingUtil.bind(itemView);
    }


}