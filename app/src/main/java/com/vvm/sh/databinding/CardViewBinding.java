package com.vvm.sh.databinding;

import android.view.View;

import androidx.cardview.widget.CardView;
import androidx.databinding.BindingAdapter;

public class CardViewBinding {


    @BindingAdapter({"ativo"})
    public static void setAtivo(CardView view, boolean estado) {

        view.setEnabled(estado);
    }


    @BindingAdapter({"condicaoVisibilidadeA", "condicaoVisibilidadeB"})
    public static void setCondicaoVisibilidade(CardView view, boolean condicaoVisibilidadeA, boolean condicaoVisibilidadeB) {


        if(condicaoVisibilidadeA == true & condicaoVisibilidadeB == false){
            view.setVisibility(View.VISIBLE);
        }
        else{
            view.setVisibility(View.GONE);
        }
    }

}
