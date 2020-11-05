package com.vvm.sh.databinding;

import android.widget.ImageView;

import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jaredrummler.materialspinner.MaterialSpinner;
import com.vvm.sh.R;
import com.vvm.sh.baseDados.entidades.Morada;
import com.vvm.sh.ui.quadroPessoal.adaptadores.ColaboradorRecyclerAdapter;
import com.vvm.sh.ui.quadroPessoal.modelos.ColaboradorRegisto;
import com.vvm.sh.ui.quadroPessoal.adaptadores.OnColaboradorListener;
import com.vvm.sh.util.constantes.Sintaxe;

import java.util.List;

public class QuadroPessoalBinding {

    @BindingAdapter({"colaboradores" , "listener"})
    public static void setColaboradores(RecyclerView view, List<ColaboradorRegisto> items, OnColaboradorListener listener) {

        if(items == null){
            return;
        }

        RecyclerView.LayoutManager layoutManager = view.getLayoutManager();

        if(layoutManager == null){
            view.setLayoutManager(new LinearLayoutManager(view.getContext()));
        }

        ColaboradorRecyclerAdapter adapter = (ColaboradorRecyclerAdapter) view.getAdapter();

        if(adapter == null){
            adapter = new ColaboradorRecyclerAdapter(view.getContext(), items, listener);

            view.setAdapter(adapter);


        }
        else{
            adapter.atualizar(items);
        }

    }


    @BindingAdapter({"estadoAdmissao"})
    public static void setEstadoAdmissao(ImageView view, int estadoAdmissao) {

        switch (estadoAdmissao){

            default:

                break;
        }
    }


    @BindingAdapter({"estadoAdmissao"})
    public static void setEstadoAdmissao(ImageView view, ColaboradorRegisto registo) {

        if(registo.estado.equals(Sintaxe.Palavras.ADMITIDO) == true || registo.estado.equals(Sintaxe.Palavras.ADMISSAO) == true){

            view.setBackgroundResource(R.drawable.ic_novo_colaborador);

            //this.imagemSituacao = R.drawable.user_add;
        }
        else if(registo.estado.equals(Sintaxe.Palavras.DEMITIDO) == true){
            //this.imagemSituacao = R.drawable.user_delete;
            view.setBackgroundResource(R.drawable.ic_demitido);
        }
        else if(registo.estado.equals(Sintaxe.Palavras.ESTADO_TRANSITADO) == true){
            view.setBackgroundResource(R.drawable.ic_transitou);
        }
        else if(registo.estado.equals(Sintaxe.Palavras.READEMITIDO) == true){
            //this.imagemSituacao = R.drawable.user_edit;
            view.setBackgroundResource(R.drawable.ic_readmitido);
        }
        else if(registo.estado.equals(Sintaxe.Palavras.QUADRO_PESSOAL_INICIAL) == true){
            //this.imagemSituacao = R.drawable.user;
            view.setBackgroundResource(R.drawable.ic_qp_inicial);
        }
    }



    @BindingAdapter({"moradas", "idMorada"})
    public static void setTipos_(MaterialSpinner view, List<Morada> registos, String idMorada) {

        if (registos == null)
            return;

        view.setItems(registos);

        if(idMorada != null) {

            for (int index = 0; index < registos.size(); ++index) {

                if(registos.get(index).id.equals(idMorada)){
                    view.setSelectedIndex(index);
                    break;
                }
            }
        }
    }

}
