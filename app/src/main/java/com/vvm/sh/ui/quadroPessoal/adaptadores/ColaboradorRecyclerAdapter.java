package com.vvm.sh.ui.quadroPessoal.adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.vvm.sh.R;
import com.vvm.sh.databinding.ItemColaboradorBinding;
import com.vvm.sh.databinding.ItemExaustoBinding;
import com.vvm.sh.databinding.ItemLoadingBinding;
import com.vvm.sh.ui.quadroPessoal.modelos.ColaboradorRegisto;
import com.vvm.sh.util.adaptadores.ExaustoViewHolder;
import com.vvm.sh.util.adaptadores.LoadingViewHolder;
import com.vvm.sh.util.interfaces.EstadoModelo;

import java.util.ArrayList;
import java.util.List;

public class ColaboradorRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    private List<ColaboradorRegisto> items = new ArrayList<>();
    private Context contexto;
    private OnColaboradorListener onItemListener;

    private int estado = EstadoModelo.MODELO;

    public ColaboradorRecyclerAdapter(Context contexto, List<ColaboradorRegisto> items, OnColaboradorListener onItemListener) {
        this.items = items;
        this.contexto = contexto;
        this.onItemListener = onItemListener;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        switch (viewType){

            case EstadoModelo.LOADING:

                ItemLoadingBinding itemLoadingBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_loading, parent, false);
                return new LoadingViewHolder(itemLoadingBinding.getRoot());


            case EstadoModelo.EXHAUSTED:

                ItemExaustoBinding itemExaustoBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_exausto, parent, false);
                return new ExaustoViewHolder(itemExaustoBinding.getRoot());


            default:
                ItemColaboradorBinding binding = DataBindingUtil.inflate(LayoutInflater.from(contexto), R.layout.item_colaborador, parent, false);
                return new ColaboradorViewHolder(binding.getRoot(), this.onItemListener);
        }

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {


        switch (getItemViewType(position)){

            case EstadoModelo.LOADING:

                ((LoadingViewHolder) holder).binding.executePendingBindings();
                break;

            case EstadoModelo.EXHAUSTED:

                ((ExaustoViewHolder) holder).binding.executePendingBindings();
                break;

            default:

                ColaboradorRegisto registo = items.get(position);
                ((ColaboradorViewHolder) holder).binding.setColaborador(registo);
                ((ColaboradorViewHolder) holder).binding.setListener((OnColaboradorListener) contexto);

                ((ColaboradorViewHolder) holder).binding.executePendingBindings();
                break;
        }

    }

    @Override
    public int getItemViewType(int position) {
        return this.items.get(position).obterEstado();
    }



    @Override
    public int getItemCount() {
        return items.size();
    }


    public void atualizar(List<ColaboradorRegisto> items) {

        int original = this.items.size();
        int novo = items.size();
        //this.items.clear();
        //this.items.addAll(lolo);

        this.items = items;
        manageQueryResult(original, novo);

        notifyDataSetChanged();
    }


    //-------------------
    //
    //-------------------


    public void displayLoading(){

        if(items.get(items.size() - 1).obterEstado() != EstadoModelo.EXHAUSTED) {

            if (!isLoading()) {

                items.add(new ColaboradorRegisto(EstadoModelo.LOADING));
                notifyDataSetChanged();
            }
        }
    }

    private boolean isLoading(){

        if(items != null){
            if (items.size() > 0) {
                if (items.get(items.size() - 1).obterEstado() == EstadoModelo.LOADING) {
                    return true;
                }
            }
        }
        return false;
    }


    public void hideLoading_(){
        if(isLoading()){

            if (items.get(items.size() - 1).obterEstado() == EstadoModelo.LOADING) {
                items.remove(items.size() - 1);
            }
        }
    }



    private void manageQueryResult(int original, int novo){

        if(original == (novo + 1)){

            setQueryExhausted_();
        }
        else{
            hideLoading_();
        }
    }


    public void setQueryExhausted_(){

        hideLoading_();

        try {
            if (items.get(items.size() - 1).obterEstado() != EstadoModelo.EXHAUSTED) {
                ColaboradorRegisto item = new ColaboradorRegisto(EstadoModelo.EXHAUSTED);
                items.add(item);
            }
        }
        catch (IndexOutOfBoundsException e){}
    }
}