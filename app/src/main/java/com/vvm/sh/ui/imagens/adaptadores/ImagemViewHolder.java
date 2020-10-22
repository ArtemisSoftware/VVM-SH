package com.vvm.sh.ui.imagens.adaptadores;

import android.view.View;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.vvm.sh.databinding.ItemBibliotecaBinding;
import com.vvm.sh.databinding.ItemImagemBinding;

public class ImagemViewHolder extends RecyclerView.ViewHolder{

    ItemImagemBinding binding;

    public ImagemViewHolder(View itemView) {
        super(itemView);
        binding = DataBindingUtil.bind(itemView);

    }
}
