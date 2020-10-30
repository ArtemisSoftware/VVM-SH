package com.vvm.sh.ui.pesquisa.adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.vvm.sh.R;
import com.vvm.sh.baseDados.entidades.Tipo;
import com.vvm.sh.databinding.ItemExaustoBinding;
import com.vvm.sh.databinding.ItemLoadingBinding;
import com.vvm.sh.databinding.ItemPesquisaBinding;
import com.vvm.sh.ui.quadroPessoal.modelos.ColaboradorRegisto;
import com.vvm.sh.util.adaptadores.ExaustoViewHolder;
import com.vvm.sh.util.adaptadores.LoadingViewHolder;
import com.vvm.sh.util.interfaces.EstadoModelo;

import java.util.ArrayList;
import java.util.List;

public class PesquisaRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    private List<Tipo> items = new ArrayList<>();
    private Context contexto;
    private OnPesquisaListener.OnPesquisaRegistoListener onItemRegistoListener;
    private OnPesquisaListener.OnPesquisaSelecionadoListener onItemSelecionadoListener;

    public PesquisaRecyclerAdapter(Context contexto, List<Tipo> items, OnPesquisaListener.OnPesquisaRegistoListener onItemListener) {
        this.items = items;
        this.contexto = contexto;
        this.onItemRegistoListener = onItemListener;
    }

    public PesquisaRecyclerAdapter(Context contexto, List<Tipo> items, OnPesquisaListener.OnPesquisaSelecionadoListener onItemListener) {
        this.items = items;
        this.contexto = contexto;
        this.onItemSelecionadoListener = onItemListener;
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

                ItemPesquisaBinding binding = DataBindingUtil.inflate(LayoutInflater.from(contexto), R.layout.item_pesquisa, parent, false);

                if(onItemSelecionadoListener == null) {
                    return new PesquisaViewHolder(binding.getRoot(), this.onItemRegistoListener);
                }
                else{
                    return new PesquisaViewHolder(binding.getRoot(), this.onItemSelecionadoListener);
                }

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

                Tipo registo = items.get(position);
                ((PesquisaViewHolder) holder).binding.setTipo(registo);
                ((PesquisaViewHolder) holder).binding.executePendingBindings();
                break;
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public int getItemViewType(int position) {
        return this.items.get(position).obterEstado();
    }

    public void atualizar(List<Tipo> items) {

        int original = this.items.size();
        int novo = items.size();
        if(original != novo) {
            this.items.clear();
            this.items.addAll(items);
        }
        manageQueryResult(original, novo);
        notifyDataSetChanged();
    }


    //-------------------
    //
    //-------------------

    public void displayLoading(){

        if(items.get(items.size() - 1).obterEstado() != EstadoModelo.EXHAUSTED) {

            if (!isLoading()) {

                items.add(new Tipo(EstadoModelo.LOADING));
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

    private void hideLoading_(){
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
                Tipo item = new Tipo(EstadoModelo.EXHAUSTED);
                items.add(item);
            }
        }
        catch(ArrayIndexOutOfBoundsException e){}
    }
}