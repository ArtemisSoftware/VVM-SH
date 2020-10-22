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

import java.util.ArrayList;
import java.util.List;

public class ImagemRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{


    private List<ImagemResultado> items = new ArrayList<>();
    private Context contexto;
    //private OnCrossSellingListener onItemListener;


    public ImagemRecyclerAdapter(Context contexto, List<ImagemResultado> items/*, OnCrossSellingListener onItemListener*/) {
        this.items = items;
        this.contexto = contexto;
        //this.onItemListener = onItemListener;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        ItemImagemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(contexto), R.layout.item_imagem, parent, false);
        return new ImagemViewHolder(binding.getRoot()/*, this.onItemListener*/);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        ImagemResultado registo = items.get(position);
        ((ImagemViewHolder)holder).binding.setImagem(registo);
//        ((ImagemViewHolder)holder).binding.setListener((OnCrossSellingListener) contexto);
//        ((ImagemViewHolder)holder).binding.setBloquear(PreferenciasUtil.agendaEditavel(contexto));
        ((ImagemViewHolder)holder).binding.executePendingBindings();


    }

    @Override
    public int getItemCount() {
        return items.size();
    }


    public void atualizar(List<ImagemResultado> items){
        this.items.clear();
        this.items.addAll(items);
        notifyDataSetChanged();
    }



}
