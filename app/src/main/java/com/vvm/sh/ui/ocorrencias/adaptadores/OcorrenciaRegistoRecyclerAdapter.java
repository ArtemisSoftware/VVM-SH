package com.vvm.sh.ui.ocorrencias.adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.vvm.sh.R;
import com.vvm.sh.databinding.ItemOcorrenciaRegistoBinding;
import com.vvm.sh.ui.ocorrencias.modelos.OcorrenciaRegisto;

import java.util.ArrayList;
import java.util.List;

public class OcorrenciaRegistoRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{


    private List<OcorrenciaRegisto> items = new ArrayList<>();
    private Context contexto;
    private OnOcorrenciaRegistoListener listenerRegisto;
    private OnOcorrenciaListener listenerOcorrencia;


    public OcorrenciaRegistoRecyclerAdapter(Context contexto, List<OcorrenciaRegisto> items, OnOcorrenciaRegistoListener listener) {
        this.items = items;
        this.contexto = contexto;
        this.listenerRegisto = listener;
    }

    public OcorrenciaRegistoRecyclerAdapter(Context contexto, List<OcorrenciaRegisto> items, OnOcorrenciaListener listener) {
        this.items = items;
        this.contexto = contexto;
        this.listenerOcorrencia = listener;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        ItemOcorrenciaRegistoBinding binding = DataBindingUtil.inflate(LayoutInflater.from(contexto), R.layout.item_ocorrencia_registo, parent, false);

        if(this.listenerRegisto != null) {

            return new OcorrenciaRegistoViewHolder(binding.getRoot(), listenerRegisto);
        }
        else{
            return new OcorrenciaRegistoViewHolder(binding.getRoot(), listenerOcorrencia);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        OcorrenciaRegisto registo = items.get(position);
        ((OcorrenciaRegistoViewHolder)holder).binding.setOcorrencia(registo);
        ((OcorrenciaRegistoViewHolder)holder).binding.executePendingBindings();

    }

    @Override
    public int getItemCount() {
        return items.size();
    }


    public void atualizar(List<OcorrenciaRegisto> items){
        this.items.clear();
        this.items.addAll(items);
        notifyDataSetChanged();
    }
}
