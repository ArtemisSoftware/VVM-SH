package com.vvm.sh.ui.atividadesPendentes.relatorios.equipamentos.adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.vvm.sh.R;
import com.vvm.sh.databinding.ItemPesquisaEquipamentoBinding;
import com.vvm.sh.ui.atividadesPendentes.relatorios.equipamentos.Equipamento;
import com.vvm.sh.ui.pesquisa.OnPesquisaListener;

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

        ItemPesquisaEquipamentoBinding binding = DataBindingUtil.inflate(LayoutInflater.from(contexto), R.layout.item_pesquisa_equipamento, parent, false);


        if(registolistener == null) {
            return new EquipamentoViewHolder(binding.getRoot(), this.selecionadolistener);
        }
        else{
            return new EquipamentoViewHolder(binding.getRoot(), this.registolistener);
        }

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        Equipamento registo = items.get(position);
        ((EquipamentoViewHolder)holder).binding.setEquipamento(registo);
        ((EquipamentoViewHolder)holder).binding.executePendingBindings();

    }

    @Override
    public int getItemCount() {
        return items.size();
    }


    public void atualizar(List<Equipamento> items){
        this.items.clear();
        this.items.addAll(items);
        notifyDataSetChanged();
    }



}
