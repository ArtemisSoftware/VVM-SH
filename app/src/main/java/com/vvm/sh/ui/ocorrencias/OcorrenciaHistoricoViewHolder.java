package com.vvm.sh.ui.ocorrencias;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.vvm.sh.R;
import com.vvm.sh.databinding.ItemOcorrenciaHistoricoBinding;
import com.vvm.sh.ui.ocorrencias.modelos.Ocorrencia;
import com.vvm.sh.util.adaptadores.Item;
import com.vvm.sh.util.adaptadores.ItemViewHolder;

import butterknife.BindView;

public class OcorrenciaHistoricoViewHolder extends RecyclerView.ViewHolder {


    public ItemOcorrenciaHistoricoBinding binding;

    public OcorrenciaHistoricoViewHolder(@NonNull View itemView) {
        super(itemView);

        binding = DataBindingUtil.bind(itemView);
    }

}
