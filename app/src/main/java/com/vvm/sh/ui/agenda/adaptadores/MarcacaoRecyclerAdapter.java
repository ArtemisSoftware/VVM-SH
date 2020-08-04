package com.vvm.sh.ui.agenda.adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.vvm.sh.R;
import com.vvm.sh.databinding.ItemMarcacaoBinding;
import com.vvm.sh.ui.agenda.modelos.Marcacao;

import java.util.ArrayList;
import java.util.List;

public class MarcacaoRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    private List<Marcacao> items = new ArrayList<>();
    private Context contexto;

    private OnAgendaListener listener;


    public MarcacaoRecyclerAdapter(Context contexto, List<Marcacao> items, OnAgendaListener listener) {
        this.items = items;
        this.contexto = contexto;
        this.listener = listener;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        ItemMarcacaoBinding binding = DataBindingUtil.inflate(LayoutInflater.from(contexto), R.layout.item_marcacao, parent, false);
        return new MarcacaoViewHolder(binding.getRoot(), listener);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        Marcacao registo = items.get(position);
        ((MarcacaoViewHolder)holder).binding.setMarcacao(registo);
        ((MarcacaoViewHolder)holder).binding.setListener((OnAgendaListener) contexto);

        ((MarcacaoViewHolder)holder).binding.executePendingBindings();


    }

    @Override
    public int getItemCount() {
        return items.size();
    }


    public void atualizar(List<Marcacao> items){
        this.items.clear();
        this.items.addAll(items);
        notifyDataSetChanged();
    }


}
