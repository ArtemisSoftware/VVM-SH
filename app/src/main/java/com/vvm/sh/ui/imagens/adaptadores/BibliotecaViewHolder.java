package com.vvm.sh.ui.imagens.adaptadores;

import android.view.View;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.vvm.sh.databinding.ItemBibliotecaBinding;

public class BibliotecaViewHolder extends RecyclerView.ViewHolder{

    ItemBibliotecaBinding binding;

    public BibliotecaViewHolder(View itemView) {
        super(itemView);
        binding = DataBindingUtil.bind(itemView);

    }
}
