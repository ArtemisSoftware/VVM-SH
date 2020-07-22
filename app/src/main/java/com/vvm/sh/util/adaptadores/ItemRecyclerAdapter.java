package com.vvm.sh.util.adaptadores;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public abstract class ItemRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    protected List<Item> registos;


    public ItemRecyclerAdapter() {

        this.registos = new ArrayList<>();
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(obterLayout(viewType), parent, false);
        return obterViewHolder(view, viewType);
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

    /**
     * Metodo que permite adicionar registos à lista de registos
     * @param registos uma lista de registos
     */
    public void fixarRegistos(List<Item> registos){
        this.registos.addAll(registos);
        notifyDataSetChanged();
    }


    /**
     * Metodo que permite apagar os registos e adicionar novos registos à lista de registos
     * @param registos uma lista de registos
     */
    public void renovarRegistos(List<Item> registos){
        this.registos.clear();
        this.registos.addAll(registos);
        notifyDataSetChanged();
    }



    /**
     * Metodo que permite obter um registo
     * @param posicao a posicao do registo
     * @return um registo
     * @throws IndexOutOfBoundsException
     */
    public Item obterRegisto(int posicao) throws IndexOutOfBoundsException{

        return this.registos.get(posicao);
    }

    //-------------------
    //Metodos abstratos
    //-------------------

    protected abstract int obterLayout(int viewType);

    protected abstract ItemViewHolder obterViewHolder(View view, int viewType);
}
