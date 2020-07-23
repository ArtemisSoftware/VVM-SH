package com.vvm.sh.ui.ocorrencias;

import android.view.View;

import com.vvm.sh.R;
import com.vvm.sh.ui.ocorrencias.modelos.Ocorrencia;
import com.vvm.sh.util.adaptadores.ItemRecyclerAdapter;
import com.vvm.sh.util.adaptadores.ItemViewHolder;
import com.vvm.sh.util.interfaces.OnCheckBoxItemListener;
import com.vvm.sh.util.interfaces.OnItemListener;

public class OcorrenciaRecyclerAdapter extends ItemRecyclerAdapter {


    private OnItemListener onItemListener;
    private OnCheckBoxItemListener onCheckBoxItemListener;

    public OcorrenciaRecyclerAdapter(OnItemListener onItemListener) {

        this.onItemListener = onItemListener;
    }

    public OcorrenciaRecyclerAdapter(OnItemListener onItemListener, OnCheckBoxItemListener onCheckBoxItemListener ) {

        this.onItemListener = onItemListener;
        this.onCheckBoxItemListener = onCheckBoxItemListener;
    }

    @Override
    protected int obterLayout(int viewType) {
        switch (viewType){

//            case Ocorrencia.TIPO_NOVA_OCORRENCIA:{
//                return R.layout.ocorrencia_nova_list_card_item;
//            }
//
//            case Ocorrencia.TIPO_HISTORICO_OCORRENCIA:{
//                return R.layout.ocorrencia_historico_list_card_item;
//            }
//
//            case Ocorrencia.TIPO_REGISTO_NOVA_OCORRENCIA:{
//                return R.layout.ocorrencia_registo_list_item;
//            }
//
//            case Ocorrencia.TIPO_TIPIFICACAO:{
//                return R.layout.ocorrencia_tipificacao_list_item;
//            }

            default:{
                return R.layout.item_ocorrencia;
            }
        }
    }


    @Override
    protected ItemViewHolder obterViewHolder(View view, int viewType) {

        switch (viewType){

//            case Ocorrencia.TIPO_NOVA_OCORRENCIA:{
//                return new OcorrenciaNovaViewHolder(view);
//            }
//
//            case Ocorrencia.TIPO_HISTORICO_OCORRENCIA:{
//                return new OcorrenciaHistoricoViewHolder(view);
//            }
//
//            case Ocorrencia.TIPO_REGISTO_NOVA_OCORRENCIA:{
//                return new OcorrenciaRegistoViewHolder(view, onCheckBoxItemListener);
//            }
//
//            case Ocorrencia.TIPO_TIPIFICACAO:{
//                return new OcorrenciaTipificacaoViewHolder(view, this.onItemListener);
//            }

            default:{
                return new OcorrenciaViewHolder(view, this.onItemListener);
            }
        }
    }


    @Override
    public int getItemViewType(int position) {
//
//        if(this.registos.size() == 0){
//            return Ocorrencia.TIPO_OCORRENCIA;
//        }
//        else{
//            return ((Ocorrencia) this.registos.get(position)).obterTipo();
//        }

        return 0;
    }


}
