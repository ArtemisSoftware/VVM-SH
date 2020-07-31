package com.vvm.sh.ui.atividadesPendentes.adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.vvm.sh.R;
import com.vvm.sh.databinding.ItemAtividadePendenteBinding;
import com.vvm.sh.ui.atividadesPendentes.modelos.AtividadePendente;
import com.vvm.sh.ui.atividadesPendentes.modelos.AtividadePendenteRegisto;

import java.util.ArrayList;
import java.util.List;

public class AtividadePendenteRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{


    private List<AtividadePendenteRegisto> items = new ArrayList<>();
    private Context contexto;
    private OnAtividadePendenteListener listener;

    public AtividadePendenteRecyclerAdapter(Context contexto, List<AtividadePendenteRegisto> items, OnAtividadePendenteListener listener) {
        this.items = items;
        this.contexto = contexto;
        this.listener = listener;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        ItemAtividadePendenteBinding binding = DataBindingUtil.inflate(LayoutInflater.from(contexto), R.layout.item_atividade_pendente, parent, false);
        return new AtividadePendenteViewHolder(binding.getRoot(), listener);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        AtividadePendenteRegisto registo = items.get(position);
        ((AtividadePendenteViewHolder)holder).binding.setAtividade(registo);
        ((AtividadePendenteViewHolder)holder).binding.setListener(listener);
        ((AtividadePendenteViewHolder)holder).binding.executePendingBindings();

    }

    @Override
    public int getItemCount() {
        return items.size();
    }


    public void atualizar(List<AtividadePendenteRegisto> items){
        this.items.clear();
        this.items.addAll(items);
        notifyDataSetChanged();
    }


}
