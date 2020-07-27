package com.vvm.sh.ui.ocorrencias.adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.vvm.sh.R;
import com.vvm.sh.databinding.ItemOcorrenciaRegistoBinding;
import com.vvm.sh.ui.opcoes.modelos.Tipo;

import java.util.ArrayList;
import java.util.List;

public class OcorrenciaRegistoRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{


    private List<Tipo> items = new ArrayList<>();
    private Context contexto;

    public OcorrenciaRegistoRecyclerAdapter(Context contexto, List<Tipo> items) {
        this.items = items;
        this.contexto = contexto;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        ItemOcorrenciaRegistoBinding binding = DataBindingUtil.inflate(LayoutInflater.from(contexto), R.layout.item_ocorrencia_registo, parent, false);
        return new OcorrenciaRegistoViewHolder(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        Tipo registo = items.get(position);
        ((OcorrenciaRegistoViewHolder)holder).binding.setTipo(registo);

        ((OcorrenciaRegistoViewHolder)holder).binding.executePendingBindings();

    }

    @Override
    public int getItemCount() {
        return items.size();
    }


    public void atualizar(List<Tipo> items){
        this.items.clear();
        this.items.addAll(items);
        notifyDataSetChanged();
    }
}
