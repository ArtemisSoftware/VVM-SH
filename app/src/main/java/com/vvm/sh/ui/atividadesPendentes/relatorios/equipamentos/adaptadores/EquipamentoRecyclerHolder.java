package com.vvm.sh.ui.atividadesPendentes.relatorios.equipamentos.adaptadores;

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
import com.vvm.sh.databinding.ItemPesquisaEquipamentoBinding;
import com.vvm.sh.ui.atividadesPendentes.relatorios.equipamentos.modelos.Equipamento;
import com.vvm.sh.ui.pesquisa.adaptadores.OnPesquisaListener;
import com.vvm.sh.util.adaptadores.ExaustoViewHolder;
import com.vvm.sh.util.adaptadores.LoadingViewHolder;
import com.vvm.sh.util.interfaces.EstadoModelo;

import java.util.ArrayList;
import java.util.List;

public class EquipamentoRecyclerHolder extends RecyclerView.Adapter<RecyclerView.ViewHolder>{


    private List<Equipamento> items = new ArrayList<>();
    private Context contexto;
    private OnPesquisaListener.OnPesquisaEquipamentoRegistoListener registolistener;
    private OnPesquisaListener.OnPesquisaEquipamentoSelecionadoListener selecionadolistener;

    public EquipamentoRecyclerHolder(Context contexto, List<Equipamento> items, OnPesquisaListener.OnPesquisaEquipamentoRegistoListener listener) {
        this.items = items;
        this.contexto = contexto;
        this.registolistener = listener;
    }

    public EquipamentoRecyclerHolder(Context contexto, List<Equipamento> items, OnPesquisaListener.OnPesquisaEquipamentoSelecionadoListener listener) {
        this.items = items;
        this.contexto = contexto;
        this.selecionadolistener = listener;
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

                ItemPesquisaEquipamentoBinding binding = DataBindingUtil.inflate(LayoutInflater.from(contexto), R.layout.item_pesquisa_equipamento, parent, false);

                if(registolistener == null) {
                    return new EquipamentoViewHolder(binding.getRoot(), this.selecionadolistener);
                }
                else{
                    return new EquipamentoViewHolder(binding.getRoot(), this.registolistener);
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

                Equipamento registo = items.get(position);
                ((EquipamentoViewHolder)holder).binding.setEquipamento(registo);
                ((EquipamentoViewHolder)holder).binding.executePendingBindings();
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



    public void atualizar(List<Equipamento> items){

        int original = this.items.size();
        int novo = items.size();

        this.items.clear();
        this.items.addAll(items);
        manageQueryResult(original, novo);
        notifyDataSetChanged();
    }





    //-------------------
    //
    //-------------------

    public void displayLoading(){

        if(items.get(items.size() - 1).obterEstado() != EstadoModelo.EXHAUSTED) {

            if (!isLoading()) {

                items.add(new Equipamento(EstadoModelo.LOADING));
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
                Equipamento item = new Equipamento(EstadoModelo.EXHAUSTED);
                items.add(item);
            }
        }
        catch(ArrayIndexOutOfBoundsException e){}
    }


}
