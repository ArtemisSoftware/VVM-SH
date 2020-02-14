package com.vvm.sh.ui.agenda.adaptadores;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.vvm.sh.R;
import com.vvm.sh.ui.agenda.Tarefa;
import com.vvm.sh.util.adaptadores.OnItemListener;

import java.util.ArrayList;
import java.util.List;

public class TarefaRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{


    private List<Tarefa> registos;


    private OnItemListener onItemListener;


    public TarefaRecyclerAdapter(OnItemListener onItemListener) {

        this.registos = new ArrayList<>();
        this.onItemListener = onItemListener;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.agenda_list_card_item, parent, false);
        return new TarefaViewHolder(view, this.onItemListener);
    }


    //-------------------
    //Metodos locais
    //-------------------

    public void fixarRegistos(List<Tarefa> registos){
        this.registos.addAll(registos);
        notifyDataSetChanged();
    }



    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((TarefaViewHolder) holder).onBind(this.registos.get(position));
    }

    @Override
    public int getItemCount() {
        return registos.size();
    }
}
