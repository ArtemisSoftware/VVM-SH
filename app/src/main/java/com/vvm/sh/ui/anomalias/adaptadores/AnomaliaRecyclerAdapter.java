package com.vvm.sh.ui.anomalias.adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.vvm.sh.R;
import com.vvm.sh.databinding.ItemAnomaliaBinding;
import com.vvm.sh.baseDados.entidades.Anomalia;

import java.util.ArrayList;
import java.util.List;

public class AnomaliaRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{


    private List<Anomalia> items = new ArrayList<>();
    private Context contexto;

    public AnomaliaRecyclerAdapter(Context contexto, List<Anomalia> items) {
        this.items = items;
        this.contexto = contexto;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        ItemAnomaliaBinding binding = DataBindingUtil.inflate(LayoutInflater.from(contexto), R.layout.item_anomalia, parent, false);
        return new AnomaliaViewHolder(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        Anomalia registo = items.get(position);
        ((AnomaliaViewHolder)holder).binding.setAnomalia(registo);
        ((AnomaliaViewHolder)holder).binding.executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return items.size();
    }


    public void atualizar(List<Anomalia> items){
        this.items.clear();
        this.items.addAll(items);
        notifyDataSetChanged();
    }



}
