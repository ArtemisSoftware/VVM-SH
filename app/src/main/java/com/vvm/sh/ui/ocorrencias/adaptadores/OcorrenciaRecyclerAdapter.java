package com.vvm.sh.ui.ocorrencias.adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.vvm.sh.R;
import com.vvm.sh.databinding.ItemOcorrenciaBinding;
import com.vvm.sh.baseDados.entidades.Ocorrencia;

import java.util.ArrayList;
import java.util.List;

public class OcorrenciaRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{


    private List<Ocorrencia> items = new ArrayList<>();
    private Context contexto;
    private OnOcorrenciaListener onItemListener;

    public OcorrenciaRecyclerAdapter(Context contexto, List<Ocorrencia> items, OnOcorrenciaListener onItemListener) {
        this.items = items;
        this.contexto = contexto;
        this.onItemListener = onItemListener;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        ItemOcorrenciaBinding binding = DataBindingUtil.inflate(LayoutInflater.from(contexto), R.layout.item_ocorrencia, parent, false);
        return new OcorrenciaViewHolder(binding.getRoot(), onItemListener);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        Ocorrencia registo = items.get(position);
        ((OcorrenciaViewHolder)holder).binding.setOcorrencia(registo);

        ((OcorrenciaViewHolder)holder).binding.executePendingBindings();

    }

    @Override
    public int getItemCount() {
        return items.size();
    }


    public void atualizar(List<Ocorrencia> items){
        this.items.clear();
        this.items.addAll(items);
        notifyDataSetChanged();
    }


}
