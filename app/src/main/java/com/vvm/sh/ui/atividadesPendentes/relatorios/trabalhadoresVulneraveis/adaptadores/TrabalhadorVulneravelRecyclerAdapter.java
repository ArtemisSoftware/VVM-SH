package com.vvm.sh.ui.atividadesPendentes.relatorios.trabalhadoresVulneraveis.adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.vvm.sh.R;
import com.vvm.sh.databinding.ItemTrabalhadorVulneravelBinding;
import com.vvm.sh.ui.atividadesPendentes.relatorios.trabalhadoresVulneraveis.modelos.TrabalhadorVulneravel;
import com.vvm.sh.ui.registoVisita.adaptadores.TrabalhoRealizadoViewHolder;
import com.vvm.sh.util.metodos.PreferenciasUtil;

import java.util.ArrayList;
import java.util.List;

public class TrabalhadorVulneravelRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{


    private List<TrabalhadorVulneravel> items = new ArrayList<>();
    private Context contexto;
    private OnTrabalhadorVulneravelListener listener;

    public TrabalhadorVulneravelRecyclerAdapter(Context contexto, List<TrabalhadorVulneravel> items, OnTrabalhadorVulneravelListener listener) {
        this.items = items;
        this.contexto = contexto;
        this.listener = listener;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        ItemTrabalhadorVulneravelBinding binding = DataBindingUtil.inflate(LayoutInflater.from(contexto), R.layout.item_trabalhador_vulneravel, parent, false);
        return new TrabalhadorVulneravelViewHolder(binding.getRoot(), listener);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        TrabalhadorVulneravel registo = items.get(position);
        ((TrabalhadorVulneravelViewHolder)holder).binding.setVulnerabilidade(registo);
        ((TrabalhadorVulneravelViewHolder)holder).binding.executePendingBindings();

    }

    @Override
    public int getItemCount() {
        return items.size();
    }


    public void atualizar(List<TrabalhadorVulneravel> items){
        this.items.clear();
        this.items.addAll(items);
        notifyDataSetChanged();
    }



}
