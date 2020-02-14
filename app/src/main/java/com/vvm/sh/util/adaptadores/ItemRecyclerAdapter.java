package com.vvm.sh.util.adaptadores;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.vvm.sh.ui.atividadesExecutadas.AtividadeExecutada;

import java.util.ArrayList;
import java.util.List;

public abstract class ItemRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private List<Item> registos;


    public ItemRecyclerAdapter() {

        this.registos = new ArrayList<>();
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(obterLayout(), parent, false);
        return obterViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((ItemViewHolder) holder).onBind(this.registos.get(position));
    }

    @Override
    public int getItemCount() {
        return registos.size();
    }


    //-------------------
    //Metodos locais
    //-------------------

    public void fixarRegistos(List<Item> registos){
        this.registos.addAll(registos);
        notifyDataSetChanged();
    }


    //-------------------
    //Metodos abstratos
    //-------------------

    protected abstract int obterLayout();

    protected abstract ItemViewHolder obterViewHolder(View view);
}
