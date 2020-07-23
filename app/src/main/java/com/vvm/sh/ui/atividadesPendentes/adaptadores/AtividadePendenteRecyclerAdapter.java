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

import java.util.ArrayList;
import java.util.List;

public class AtividadePendenteRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{


    private List<AtividadePendente> items = new ArrayList<>();
    private Context contexto;

    public AtividadePendenteRecyclerAdapter(Context contexto, List<AtividadePendente> items) {
        this.items = items;
        this.contexto = contexto;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        ItemAtividadePendenteBinding binding = DataBindingUtil.inflate(LayoutInflater.from(contexto), R.layout.item_atividade_pendente, parent, false);
        return new AtividadePendenteViewHolder(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        AtividadePendente registo = items.get(position);
        ((AtividadePendenteViewHolder)holder).binding.setAtividade(registo);

        ((AtividadePendenteViewHolder)holder).binding.executePendingBindings();

    }

    @Override
    public int getItemCount() {
        return items.size();
    }


    public void atualizar(List<AtividadePendente> items){
        this.items.clear();
        this.items.addAll(items);
        notifyDataSetChanged();
    }


}
