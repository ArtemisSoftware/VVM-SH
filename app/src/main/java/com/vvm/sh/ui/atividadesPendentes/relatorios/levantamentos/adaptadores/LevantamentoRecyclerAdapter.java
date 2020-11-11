package com.vvm.sh.ui.atividadesPendentes.relatorios.levantamentos.adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.vvm.sh.R;
import com.vvm.sh.databinding.ItemExaustoBinding;
import com.vvm.sh.databinding.ItemLevantamentoBinding;
import com.vvm.sh.databinding.ItemLoadingBinding;
import com.vvm.sh.ui.atividadesPendentes.relatorios.formacao.adaptadores.FormandoViewHolder;
import com.vvm.sh.ui.atividadesPendentes.relatorios.levantamentos.modelos.Levantamento;
import com.vvm.sh.ui.quadroPessoal.modelos.ColaboradorRegisto;
import com.vvm.sh.util.adaptadores.ExaustoViewHolder;
import com.vvm.sh.util.adaptadores.LoadingViewHolder;
import com.vvm.sh.util.interfaces.EstadoModelo;
import com.vvm.sh.util.metodos.PreferenciasUtil;

import java.util.ArrayList;
import java.util.List;

public class LevantamentoRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{


    private List<Levantamento> items = new ArrayList<>();
    private Context contexto;
    private OnLevantamentoListener.OnLevantamentoRegistoListener onItemListener;


    public LevantamentoRecyclerAdapter(Context contexto, List<Levantamento> items, OnLevantamentoListener.OnLevantamentoRegistoListener onItemListener) {
        this.items = items;
        this.contexto = contexto;
        this.onItemListener = onItemListener;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        switch (viewType) {
            case EstadoModelo.LOADING:

                ItemLoadingBinding itemLoadingBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_loading, parent, false);
                return new LoadingViewHolder(itemLoadingBinding.getRoot());


            case EstadoModelo.EXHAUSTED:

                ItemExaustoBinding itemExaustoBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_exausto, parent, false);
                return new ExaustoViewHolder(itemExaustoBinding.getRoot());


            default:

                ItemLevantamentoBinding binding = DataBindingUtil.inflate(LayoutInflater.from(contexto), R.layout.item_levantamento, parent, false);
                return new LevantamentoViewHolder(binding.getRoot(), this.onItemListener);
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

                Levantamento registo = items.get(position);
                ((LevantamentoViewHolder)holder).binding.setLevantamento(registo);
                ((LevantamentoViewHolder)holder).binding.setListener((OnLevantamentoListener.OnLevantamentoRegistoListener) contexto);
                ((LevantamentoViewHolder)holder).binding.executePendingBindings();

                if(PreferenciasUtil.agendaEditavel(contexto) == false){
                    ((LevantamentoViewHolder)holder).binding.chkSelecionado.setVisibility(View.GONE);
                }
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



    public void atualizar(List<Levantamento> items){

        int original = this.items.size();
        int novo = items.size();

        this.items = items;
        manageQueryResult(original, novo);
        notifyDataSetChanged();
    }


    public void selecionarTudo(boolean checked) {

        for(int index = 0; index < this.items.size(); ++index){
            this.items.get(index).selecionado = checked;
        }

        notifyDataSetChanged();
    }


    public List<Integer> obterSelecionados() {

        List<Integer> registos = new ArrayList<>();

        for(int index = 0; index < this.items.size(); ++index){

            if(this.items.get(index).selecionado == true) {
                registos.add(this.items.get(index).resultado.id);
            }
        }

        return  registos;
    }


    //-------------------
    //
    //-------------------


    public void displayLoading(){

        if(items.get(items.size() - 1).obterEstado() != EstadoModelo.EXHAUSTED) {

            if (!isLoading()) {

                items.add(new Levantamento(EstadoModelo.LOADING));
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
                Levantamento item = new Levantamento(EstadoModelo.EXHAUSTED);
                items.add(item);
            }
        }
        catch (IndexOutOfBoundsException e){}
    }
}
