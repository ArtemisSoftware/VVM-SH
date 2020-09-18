package com.vvm.sh.databinding;

import androidx.databinding.BindingAdapter;

import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipDrawable;
import com.vvm.sh.R;

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

}
