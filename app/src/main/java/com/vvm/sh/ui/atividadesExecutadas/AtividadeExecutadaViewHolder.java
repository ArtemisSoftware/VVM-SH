package com.vvm.sh.ui.atividadesExecutadas;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.vvm.sh.R;
import com.vvm.sh.databinding.ItemAtividadeExecutadaBinding;
import com.vvm.sh.util.adaptadores.Item;
import com.vvm.sh.util.adaptadores.ItemViewHolder;

import butterknife.BindView;

public class AtividadeExecutadaViewHolder extends RecyclerView.ViewHolder {


    ItemAtividadeExecutadaBinding binding;


    @BindView(R.id.txt_data)
    TextView txt_data;


    public AtividadeExecutadaViewHolder(@NonNull View itemView) {
        super(itemView);

        binding = DataBindingUtil.bind(itemView);
    }

}
