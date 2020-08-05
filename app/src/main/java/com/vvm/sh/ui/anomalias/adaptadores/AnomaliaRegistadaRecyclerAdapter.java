package com.vvm.sh.ui.anomalias.adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.vvm.sh.R;
import com.vvm.sh.databinding.ItemAnomaliaRegistadaBinding;
import com.vvm.sh.ui.anomalias.modelos.AnomaliaRegistada;

import java.util.ArrayList;
import java.util.List;

public class AnomaliaRegistadaRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{


    private List<AnomaliaRegistada> items = new ArrayList<>();
    private Context contexto;
    private OnAnomaliasListener listener;

    public AnomaliaRegistadaRecyclerAdapter(Context contexto, List<AnomaliaRegistada> items, OnAnomaliasListener listener) {
        this.items = items;
        this.contexto = contexto;
        this.listener = listener;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        ItemAnomaliaRegistadaBinding binding = DataBindingUtil.inflate(LayoutInflater.from(contexto), R.layout.item_anomalia_registada, parent, false);
        return new AnomaliaRegistadaViewHolder(binding.getRoot(), listener);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        AnomaliaRegistada registo = items.get(position);
        ((AnomaliaRegistadaViewHolder)holder).binding.setAnomalia(registo);

        ((AnomaliaRegistadaViewHolder)holder).binding.executePendingBindings();


    }

    @Override
    public int getItemCount() {
        return items.size();
    }


    public void atualizar(List<AnomaliaRegistada> items){
        this.items.clear();
        this.items.addAll(items);
        notifyDataSetChanged();
    }



}
