package com.vvm.sh.ui.ocorrencias.adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.vvm.sh.R;
import com.vvm.sh.databinding.ItemOcorrenciaBinding;
import com.vvm.sh.ui.ocorrencias.modelos.Ocorrencia;

import java.util.ArrayList;
import java.util.List;

public class OcorrenciaRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{


    private List<Ocorrencia> items = new ArrayList<>();
    private Context contexto;

    public OcorrenciaRecyclerAdapter(Context contexto, List<Ocorrencia> items) {
        this.items = items;
        this.contexto = contexto;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        ItemOcorrenciaBinding binding = DataBindingUtil.inflate(LayoutInflater.from(contexto), R.layout.item_ocorrencia, parent, false);
        return new OcorrenciaViewHolder(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        Ocorrencia registo = items.get(position);
        ((OcorrenciaViewHolder)holder).binding.setOcorrencia(registo);

        ((OcorrenciaViewHolder)holder).binding.executePendingBindings();

    }

    @Override
    public int getItemCount() {
        return items.size();
    }


    public void atualizar(List<Ocorrencia> items){
        this.items.clear();
        this.items.addAll(items);
        notifyDataSetChanged();
    }

//    private OnItemListener onItemListener;
//    private OnCheckBoxItemListener onCheckBoxItemListener;
//
//    public OcorrenciaRecyclerAdapter(OnItemListener onItemListener) {
//
//        this.onItemListener = onItemListener;
//    }
//
//    public OcorrenciaRecyclerAdapter(OnItemListener onItemListener, OnCheckBoxItemListener onCheckBoxItemListener ) {
//
//        this.onItemListener = onItemListener;
//        this.onCheckBoxItemListener = onCheckBoxItemListener;
//    }
//
//    @Override
//    protected int obterLayout(int viewType) {
//        switch (viewType){
//
////            case Ocorrencia.TIPO_NOVA_OCORRENCIA:{
////                return R.layout.ocorrencia_nova_list_card_item;
////            }
////
////            case Ocorrencia.TIPO_HISTORICO_OCORRENCIA:{
////                return R.layout.ocorrencia_historico_list_card_item;
////            }
////
////            case Ocorrencia.TIPO_REGISTO_NOVA_OCORRENCIA:{
////                return R.layout.ocorrencia_registo_list_item;
////            }
////
////            case Ocorrencia.TIPO_TIPIFICACAO:{
////                return R.layout.ocorrencia_tipificacao_list_item;
////            }
//
//            default:{
//                return R.layout.item_ocorrencia;
//            }
//        }
//    }
//
//
//    @Override
//    protected ItemViewHolder obterViewHolder(View view, int viewType) {
//
//        switch (viewType){
//
////            case Ocorrencia.TIPO_NOVA_OCORRENCIA:{
////                return new OcorrenciaNovaViewHolder(view);
////            }
////
////            case Ocorrencia.TIPO_HISTORICO_OCORRENCIA:{
////                return new OcorrenciaHistoricoViewHolder(view);
////            }
////
////            case Ocorrencia.TIPO_REGISTO_NOVA_OCORRENCIA:{
////                return new OcorrenciaRegistoViewHolder(view, onCheckBoxItemListener);
////            }
////
////            case Ocorrencia.TIPO_TIPIFICACAO:{
////                return new OcorrenciaTipificacaoViewHolder(view, this.onItemListener);
////            }
//
//            default:{
//                return new OcorrenciaViewHolder(view, this.onItemListener);
//            }
//        }
//    }
//
//
//    @Override
//    public int getItemViewType(int position) {
////
////        if(this.registos.size() == 0){
////            return Ocorrencia.TIPO_OCORRENCIA;
////        }
////        else{
////            return ((Ocorrencia) this.registos.get(position)).obterTipo();
////        }
//
//        return 0;
//    }


}
