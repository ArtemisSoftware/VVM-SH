package com.vvm.sh.ui.opcoes.adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.vvm.sh.R;
import com.vvm.sh.baseDados.entidades.TipoNovo;
import com.vvm.sh.databinding.ItemTipoChecklistBinding;
import com.vvm.sh.databinding.ItemTipoNovoBinding;
import com.vvm.sh.ui.opcoes.modelos.ResumoChecklist;

import java.util.ArrayList;
import java.util.List;

public class TipoNovoRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{


    private List<TipoNovo> items = new ArrayList<>();
    private Context contexto;

    public TipoNovoRecyclerAdapter(Context contexto, List<TipoNovo> items) {
        this.items = items;
        this.contexto = contexto;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        ItemTipoNovoBinding binding = DataBindingUtil.inflate(LayoutInflater.from(contexto), R.layout.item_tipo_novo, parent, false);
        return new TipoNovoViewHolder(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        TipoNovo registo = items.get(position);
        ((TipoNovoViewHolder)holder).binding.setTipo(registo);
        ((TipoChecklistViewHolder)holder).binding.executePendingBindings();


    }

    @Override
    public int getItemCount() {
        return items.size();
    }



}
