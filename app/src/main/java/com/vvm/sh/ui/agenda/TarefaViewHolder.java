package com.vvm.sh.ui.agenda;

import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.vvm.sh.R;
import com.vvm.sh.databinding.ItemTarefaBinding;
import com.vvm.sh.util.adaptadores.Item;
import com.vvm.sh.util.adaptadores.ItemViewHolder;
import com.vvm.sh.util.interfaces.OnItemListener;
import com.vvm.sh.util.interfaces.OnItemLongListener;

import butterknife.BindView;

public class TarefaViewHolder extends RecyclerView.ViewHolder /*implements View.OnClickListener, View.OnLongClickListener*/{


    ItemTarefaBinding binding;


    private OnItemListener onItemListener;
    private OnItemLongListener onItemLongListener;


    public TarefaViewHolder(@NonNull View itemView/*, OnItemListener onItemListener, OnItemLongListener onItemLongListener*/) {
        super(itemView);

        binding = DataBindingUtil.bind(itemView);
        //this.onItemListener = onItemListener;
        //this.onItemLongListener = onItemLongListener;
        //itemView.setOnClickListener(this);
        //itemView.setOnLongClickListener(this);
    }


//
//    @Override
//    protected void preencherCampos(Item item) {
//
//        /*
//        Tarefa registo = (Tarefa) item;
//
//        TextDrawable drawable = TextDrawable.builder()
//                .beginConfig()
//                .toUpperCase()
//                .endConfig()
//                .buildRound(registo.obterEmpresa(), Color.RED);
//        img_empresa.setImageDrawable(drawable);
//
//        */
//    }
//
//
//    @Override
//    public void onClick(View v) {
//        onItemListener.onItemClick(getAdapterPosition());
//    }
//
//    @Override
//    public boolean onLongClick(View v) {
//        onItemLongListener.onItemLongClick(getAdapterPosition());
//        return true;
//    }
}