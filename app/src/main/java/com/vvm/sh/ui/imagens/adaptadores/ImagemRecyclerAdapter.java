package com.vvm.sh.ui.imagens.adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.vvm.sh.R;
import com.vvm.sh.baseDados.entidades.ImagemResultado;
import com.vvm.sh.databinding.ItemImagemBinding;
import com.vvm.sh.ui.imagens.modelos.ImagemRegisto;

import java.util.ArrayList;
import java.util.List;

public class ImagemRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{


    private List<ImagemRegisto> items = new ArrayList<>();
    private Context contexto;
    private OnImagemListener onItemListener;


    public ImagemRecyclerAdapter(Context contexto, List<ImagemRegisto> items, OnImagemListener listener) {
        this.items = items;
        this.contexto = contexto;
        this.onItemListener = listener;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        ItemImagemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(contexto), R.layout.item_imagem, parent, false);
        return new ImagemViewHolder(binding.getRoot(), this.onItemListener);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        ImagemRegisto registo = items.get(position);
        ((ImagemViewHolder)holder).binding.setImagem(registo);
        ((ImagemViewHolder)holder).binding.executePendingBindings();


    }

    @Override
    public int getItemCount() {
        return items.size();
    }


    public void atualizar(List<ImagemRegisto> items){
        this.items.clear();
        this.items.addAll(items);
        notifyDataSetChanged();
    }



}
