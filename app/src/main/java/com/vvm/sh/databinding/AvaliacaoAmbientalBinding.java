package com.vvm.sh.databinding;

import android.widget.ImageView;
import android.widget.TextView;

import androidx.databinding.BindingAdapter;

import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipDrawable;
import com.vvm.sh.R;
import com.vvm.sh.util.constantes.Identificadores;

public class AvaliacaoAmbientalBinding {


    @BindingAdapter({"completudeRelatorio"})
    public static void setCompletudeRelatorio(Chip view, boolean estado) {

        ChipDrawable chipDrawable = (ChipDrawable) view.getChipDrawable();

        if(estado == true){

            view.setText(view.getContext().getString(R.string.completo));
            chipDrawable.setChipBackgroundColorResource(R.color.cor_completo);
            view.setChipIcon(view.getContext().getResources().getDrawable(R.drawable.ic_executado_24dp));

        }
        else{
            view.setText(view.getContext().getString(R.string.incompleto));
            chipDrawable.setChipBackgroundColorResource(R.color.cor_incompleto);
            view.setChipIcon(view.getContext().getResources().getDrawable(R.drawable.ic_nao_executado_24dp));
        }
    }

    @BindingAdapter({"equipamento"})
    public static void setNomeEquipamento(TextView view, int tipo) {

        switch (tipo){

            case Identificadores.Relatorios.ID_RELATORIO_ILUMINACAO:

                view.setText(view.getContext().getString(R.string.termohigrometro));
                break;

            case Identificadores.Relatorios.ID_RELATORIO_TEMPERATURA_HUMIDADE:

                view.setText(view.getContext().getString(R.string.luxometro));
                break;

            default:
                break;

        }
    }


    @BindingAdapter({"tituloRelatorio"})
    public static void setTituloRelatorio(TextView view, int tipo) {

        switch (tipo){

            case Identificadores.Relatorios.ID_RELATORIO_ILUMINACAO:

                view.setText(view.getContext().getString(R.string.iluminacao));
                break;

            case Identificadores.Relatorios.ID_RELATORIO_TEMPERATURA_HUMIDADE:

                view.setText(view.getContext().getString(R.string.temperatura_humidade));
                break;

            default:
                break;

        }
    }


    @BindingAdapter({"imagemRelatorio"})
    public static void setImagemRelatorio(ImageView view, int tipo) {

        switch (tipo){

            case Identificadores.Relatorios.ID_RELATORIO_ILUMINACAO:

                view.setBackgroundResource(R.drawable.iluminacao_banner);
                break;

            case Identificadores.Relatorios.ID_RELATORIO_TEMPERATURA_HUMIDADE:

                view.setBackgroundResource(R.drawable.termico_banner);
                break;

            default:
                break;

        }
    }

}
