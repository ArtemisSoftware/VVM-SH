package com.vvm.sh.ui.atividadesPendentes.relatorios.checklist.adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.vvm.sh.R;
import com.vvm.sh.baseDados.entidades.ItemChecklist;
import com.vvm.sh.databinding.ItemChecklistFotoBinding;
import com.vvm.sh.databinding.ItemChecklistObservacaoBinding;
import com.vvm.sh.databinding.ItemChecklistPerguntaBinding;
import com.vvm.sh.databinding.ItemChecklistUtsBinding;
import com.vvm.sh.ui.atividadesPendentes.relatorios.checklist.modelos.Item;
import com.vvm.sh.ui.atividadesPendentes.relatorios.checklist.modelos.Questao;
import com.vvm.sh.util.constantes.Identificadores;

import java.util.ArrayList;
import java.util.List;

public class QuestionarioRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{


    private List<Questao> items = new ArrayList<>();
    private Context contexto;
    private OnChecklistListener.OnQuestaoListener listener;

    private final int QUESTAO = 1;
    private final int OBSERVACAO = 2;
    private final int UTS = 3;
    private final int FOTOS = 4;


    public QuestionarioRecyclerAdapter(Context contexto, List<Questao> items, OnChecklistListener.OnQuestaoListener listener) {
        this.items = items;
        this.contexto = contexto;
        this.listener = listener;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        switch (viewType){

            case QUESTAO:

                ItemChecklistPerguntaBinding binding = DataBindingUtil.inflate(LayoutInflater.from(contexto), R.layout.item_checklist_pergunta, parent, false);
                return new PerguntaViewHolder(binding.getRoot(), listener);



            case OBSERVACAO:

                ItemChecklistObservacaoBinding bindingObs = DataBindingUtil.inflate(LayoutInflater.from(contexto), R.layout.item_checklist_observacao, parent, false);
                return new ObservacaoViewHolder(bindingObs.getRoot(), listener);



            case FOTOS:

                ItemChecklistFotoBinding bindingFotos = DataBindingUtil.inflate(LayoutInflater.from(contexto), R.layout.item_checklist_foto, parent, false);
                return new FotoViewHolder(bindingFotos.getRoot(), listener);

            case UTS:

                ItemChecklistUtsBinding bindingUt = DataBindingUtil.inflate(LayoutInflater.from(contexto), R.layout.item_checklist_uts, parent, false);
                return new UtViewHolder(bindingUt.getRoot(), listener);

            default:

                break;

        }

        return null;
    }


    @Override
    public int getItemViewType(int position) {


        int tipo = 0;
        Questao questao = items.get(position);


        switch (questao.registo.tipo){

            case Identificadores.Checklist.TIPO_QUESTAO:

                tipo = QUESTAO;
                break;


            case Identificadores.Checklist.TIPO_OBSERVACOES:

                tipo = OBSERVACAO;
                break;


            case Identificadores.Checklist.TIPO_FOTOS:

                tipo = FOTOS;
                break;

            case Identificadores.Checklist.TIPO_UTS:

                tipo = UTS;
                break;
            default:

                break;

        }


        return tipo;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        Questao registo = items.get(position);

        switch (getItemViewType(position)){

            case QUESTAO:

                ((PerguntaViewHolder)holder).binding.setQuestao(registo);
                ((PerguntaViewHolder)holder).binding.executePendingBindings();
                break;


            case OBSERVACAO:

                ((ObservacaoViewHolder)holder).binding.setQuestao(registo);
                ((ObservacaoViewHolder)holder).binding.executePendingBindings();
                break;


            case FOTOS:

                ((FotoViewHolder)holder).binding.setQuestao(registo);
                ((FotoViewHolder)holder).binding.executePendingBindings();
                break;

            case UTS:
                ((UtViewHolder)holder).binding.setQuestao(registo);
                ((UtViewHolder)holder).binding.executePendingBindings();
                break;
            default:

                break;

        }




    }

    @Override
    public int getItemCount() {
        return items.size();
    }


    public void atualizar(List<Questao> items){
        this.items.clear();
        this.items.addAll(items);
        notifyDataSetChanged();
    }



}
