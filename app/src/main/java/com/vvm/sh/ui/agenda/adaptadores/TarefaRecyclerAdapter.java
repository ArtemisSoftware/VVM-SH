package com.vvm.sh.ui.agenda.adaptadores;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.vvm.sh.R;
import com.vvm.sh.ui.agenda.Tarefa;

import java.util.ArrayList;
import java.util.List;

public class TarefaRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private List<Tarefa> registos;

    public TarefaRecyclerAdapter() {
        this.registos = new ArrayList<>();

    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.agenda_list_item, parent, false);
        return new TarefaViewHolder(view);
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
