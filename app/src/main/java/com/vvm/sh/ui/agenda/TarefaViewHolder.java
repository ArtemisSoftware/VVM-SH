package com.vvm.sh.ui.agenda;

import android.graphics.Color;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.amulyakhare.textdrawable.TextDrawable;
import com.vvm.sh.R;
import com.vvm.sh.ui.agenda.Tarefa;
import com.vvm.sh.util.adaptadores.Item;
import com.vvm.sh.util.adaptadores.ItemViewHolder;
import com.vvm.sh.util.interfaces.OnItemListener;
import com.vvm.sh.util.interfaces.OnItemLongListener;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TarefaViewHolder extends ItemViewHolder implements View.OnClickListener, View.OnLongClickListener{


    @BindView(R.id.img_empresa)
    ImageView img_empresa;


    private OnItemListener onItemListener;
    private OnItemLongListener onItemLongListener;


    public TarefaViewHolder(@NonNull View itemView, OnItemListener onItemListener, OnItemLongListener onItemLongListener) {
        super(itemView);


        this.onItemListener = onItemListener;
        this.onItemLongListener = onItemLongListener;
        itemView.setOnClickListener(this);
        itemView.setOnLongClickListener(this);
    }



    @Override
    protected void preencherCampos(Item item) {

        Tarefa registo = (Tarefa) item;

        TextDrawable drawable = TextDrawable.builder()
                .beginConfig()
                .toUpperCase()
                .endConfig()
                .buildRound(registo.obterEmpresa(), Color.RED);
        img_empresa.setImageDrawable(drawable);
    }


    @Override
    public void onClick(View v) {
        onItemListener.onItemClick(getAdapterPosition());
    }

    @Override
    public boolean onLongClick(View v) {
        onItemLongListener.onItemLongClick(getAdapterPosition());
        return true;
    }
}