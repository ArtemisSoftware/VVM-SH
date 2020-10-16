package com.vvm.sh.ui.atividadesPendentes.relatorios.propostaPlanoAccao.adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.vvm.sh.R;
import com.vvm.sh.databinding.ItemPropostaCondicoesStBinding;
import com.vvm.sh.databinding.ItemPropostaMedidaAvalicaoBinding;
import com.vvm.sh.ui.atividadesPendentes.relatorios.propostaPlanoAccao.modelos.Proposta;
import com.vvm.sh.ui.crossSelling.adaptadores.CrossSellingViewHolder;
import com.vvm.sh.util.constantes.Identificadores;
import com.vvm.sh.util.metodos.PreferenciasUtil;

import java.util.ArrayList;
import java.util.List;

public class PropostaRecyclerHolder extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    private List<Proposta> items = new ArrayList<>();
    private Context contexto;
    private OnPropostaPlanoAcaoListener listener;

    private final int ST = 1;
    private final int MEDIDAS = 2;


    public PropostaRecyclerHolder(Context contexto, List<Proposta> items) {
        this.items = items;
        this.contexto = contexto;
    }


    public PropostaRecyclerHolder(Context contexto, List<Proposta> items, OnPropostaPlanoAcaoListener listener) {
        this.items = items;
        this.contexto = contexto;
        this.listener = listener;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        switch (viewType) {

            case ST:

                ItemPropostaCondicoesStBinding binding = DataBindingUtil.inflate(LayoutInflater.from(contexto), R.layout.item_proposta_condicoes_st, parent, false);
                return new PropostaStViewHolder(binding.getRoot());


            case MEDIDAS:

                ItemPropostaMedidaAvalicaoBinding bindingMedidas = DataBindingUtil.inflate(LayoutInflater.from(contexto), R.layout.item_proposta_medida_avalicao, parent, false);
                return new PropostaMedidaViewHolder(bindingMedidas.getRoot(), listener);


        }

        return null;
    }


    @Override
    public int getItemViewType(int position) {


        int tipo = ST;
        Proposta proposta = items.get(position);


        switch (proposta.tipo){

            case Identificadores.Origens.PROPOSTA_CONDICOES_ST:

                tipo = ST;
                break;


            case Identificadores.Origens.PROPOSTA_MEDIDAS_AVALIACAO:

                tipo = MEDIDAS;
                break;

            default:

                break;

        }


        return tipo;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        Proposta proposta = items.get(position);

        switch (getItemViewType(position)) {

            case ST:

                ((PropostaStViewHolder) holder).binding.setProposta(proposta);
                ((PropostaStViewHolder) holder).binding.executePendingBindings();
                break;


            case MEDIDAS:

                ((PropostaMedidaViewHolder) holder).binding.setProposta(proposta);
                ((PropostaMedidaViewHolder)holder).binding.setBloquear(PreferenciasUtil.agendaEditavel(contexto));
                ((PropostaMedidaViewHolder) holder).binding.executePendingBindings();
                break;

            default:

                break;

        }


    }

    @Override
    public int getItemCount() {
        return items.size();
    }


    public void atualizar(List<Proposta> items) {
        this.items.clear();
        this.items.addAll(items);
        notifyDataSetChanged();
    }

}