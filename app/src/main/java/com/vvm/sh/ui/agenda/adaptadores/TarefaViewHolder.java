package com.vvm.sh.ui.agenda.adaptadores;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.vvm.sh.R;
import com.vvm.sh.ui.agenda.Tarefa;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TarefaViewHolder extends RecyclerView.ViewHolder{

    @BindView(R.id.txt_titulo)
    TextView txt_title;

    public TarefaViewHolder(@NonNull View itemView) {
        super(itemView);

        ButterKnife.bind(this, itemView);
    }

    public void onBind(Tarefa tarefa){

        txt_title.setText(tarefa.obterDescricao());
    }


}