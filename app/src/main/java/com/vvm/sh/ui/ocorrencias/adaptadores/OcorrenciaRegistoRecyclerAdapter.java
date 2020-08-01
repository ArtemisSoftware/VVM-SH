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
import com.vvm.sh.ui.opcoes.modelos.Tipo;

import java.util.ArrayList;
import java.util.List;

public class OcorrenciaRegistoRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{


    private List<OcorrenciaRegisto> items = new ArrayList<>();
    private Context contexto;
    private OnOcorrenciaRegistoListener listener;
    private boolean visualizar;

    public OcorrenciaRegistoRecyclerAdapter(Context contexto, List<OcorrenciaRegisto> items, OnOcorrenciaRegistoListener listener, boolean visualizar) {
        this.items = items;
        this.contexto = contexto;
        this.listener = listener;

        this.visualizar = visualizar;
    }


    public OcorrenciaRegistoRecyclerAdapter(Context contexto, List<OcorrenciaRegisto> items, OnOcorrenciaRegistoListener listener) {
        this.items = items;
        this.contexto = contexto;
        this.listener = listener;
        this.visualizar = false;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        ItemOcorrenciaRegistoBinding binding = DataBindingUtil.inflate(LayoutInflater.from(contexto), R.layout.item_ocorrencia_registo, parent, false);
        return new OcorrenciaRegistoViewHolder(binding.getRoot(), listener, this.visualizar);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        OcorrenciaRegisto registo = items.get(position);
        ((OcorrenciaRegistoViewHolder)holder).binding.setOcorrencia(registo);
        ((OcorrenciaRegistoViewHolder)holder).binding.setListener(listener);
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
