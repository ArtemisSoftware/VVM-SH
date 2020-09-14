package com.vvm.sh.ui.atividadesPendentes.relatorios.avaliacaoAmbiental;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.vvm.sh.R;
import com.vvm.sh.databinding.ItemAvaliacaoIluminacaoBinding;
import com.vvm.sh.databinding.ItemAvaliacaoTemperaturaHumidadeBinding;
import com.vvm.sh.util.constantes.Identificadores;
import com.vvm.sh.util.metodos.PreferenciasUtil;

import java.util.ArrayList;
import java.util.List;

public class AvaliacaoRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{


    private List<AvaliacaoAmbiental> items = new ArrayList<>();
    private Context contexto;
    private int tipo;
    private OnAvaliacaoAmbientalListener listener;

    public AvaliacaoRecyclerAdapter(Context contexto, List<AvaliacaoAmbiental> items, int tipo, OnAvaliacaoAmbientalListener listener) {
        this.items = items;
        this.contexto = contexto;
        this.tipo = tipo;
        this.listener = listener;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        if(tipo == Identificadores.Relatorios.ID_RELATORIO_ILUMINACAO) {

            ItemAvaliacaoIluminacaoBinding binding = DataBindingUtil.inflate(LayoutInflater.from(contexto), R.layout.item_avaliacao_iluminacao, parent, false);
            return new IluminacaoViewHolder(binding.getRoot(), listener);
        }
        else{
            ItemAvaliacaoTemperaturaHumidadeBinding binding = DataBindingUtil.inflate(LayoutInflater.from(contexto), R.layout.item_avaliacao_temperatura_humidade, parent, false);
            return new TemperaturaHumidadeViewHolder(binding.getRoot(), listener);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        AvaliacaoAmbiental registo = items.get(position);
        if(tipo == Identificadores.Relatorios.ID_RELATORIO_ILUMINACAO) {

            ((IluminacaoViewHolder) holder).binding.setAvaliacao(registo);
            ((IluminacaoViewHolder) holder).binding.executePendingBindings();
        }
        else {

            ((TemperaturaHumidadeViewHolder) holder).binding.setAvaliacao(registo);
            ((TemperaturaHumidadeViewHolder) holder).binding.executePendingBindings();
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }


    public void atualizar(List<AvaliacaoAmbiental> items){
        this.items.clear();
        this.items.addAll(items);
        notifyDataSetChanged();
    }



}
