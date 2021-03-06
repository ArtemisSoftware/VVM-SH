package com.vvm.sh.ui.registoVisita.adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.vvm.sh.R;
import com.vvm.sh.baseDados.entidades.Tipo;
import com.vvm.sh.databinding.ItemTrabalhoRealizadoBinding;
import com.vvm.sh.ui.registoVisita.modelos.TrabalhoRealizado;
import com.vvm.sh.util.metodos.PreferenciasUtil;

import java.util.ArrayList;
import java.util.List;

public class TrabalhoRealizadoRecyclerAdaptor extends RecyclerView.Adapter<RecyclerView.ViewHolder>{


    private List<TrabalhoRealizado> items = new ArrayList<>();
    private Context contexto;
    private OnRegistoVisitaListener onItemListener;


    public TrabalhoRealizadoRecyclerAdaptor(Context contexto, List<TrabalhoRealizado> items, OnRegistoVisitaListener onItemListener) {
        this.items = items;
        this.contexto = contexto;
        this.onItemListener = onItemListener;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        ItemTrabalhoRealizadoBinding binding = DataBindingUtil.inflate(LayoutInflater.from(contexto), R.layout.item_trabalho_realizado, parent, false);
        return new TrabalhoRealizadoViewHolder(binding.getRoot(), this.onItemListener);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        TrabalhoRealizado registo = items.get(position);
        ((TrabalhoRealizadoViewHolder)holder).binding.setTrabalho(registo);
        ((TrabalhoRealizadoViewHolder)holder).binding.setListener((OnRegistoVisitaListener) contexto);
        ((TrabalhoRealizadoViewHolder)holder).binding.setBloquear(PreferenciasUtil.agendaEditavel(contexto));
        ((TrabalhoRealizadoViewHolder)holder).binding.executePendingBindings();


    }

    @Override
    public int getItemCount() {
        return items.size();
    }


    public void atualizar(List<TrabalhoRealizado> items){
        this.items.clear();
        this.items.addAll(items);
        notifyDataSetChanged();
    }



}
