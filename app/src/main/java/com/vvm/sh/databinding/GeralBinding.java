package com.vvm.sh.databinding;

import android.content.Context;
import android.graphics.Bitmap;
import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipDrawable;
import com.jaredrummler.materialspinner.MaterialSpinner;
import com.vvm.sh.R;
import com.vvm.sh.baseDados.entidades.Morada;
import com.vvm.sh.baseDados.entidades.Tipo;
import com.vvm.sh.util.metodos.ImagemUtil;

import java.util.List;

public class GeralBinding {

    @BindingAdapter({"imagem"})
    public static void setImagem(ImageView view, byte[] imagem) {

        if(imagem == null){
            return;
        }

        view.setImageBitmap(Bitmap.createScaledBitmap(ImagemUtil.converter(imagem), 900, 300, false));

    }

    @BindingAdapter({"imagem", "omissao"})
    public static void setImagem(ImageView view, byte[] imagem, int omissao) {

        if(imagem == null){
            view.setBackgroundResource(omissao);
            return;
        }

        Bitmap bitmap = ImagemUtil.converter(imagem);

        int width = bitmap.getWidth();
        int height = bitmap.getHeight();

        view.setImageBitmap(Bitmap.createScaledBitmap(ImagemUtil.converter(imagem), width, height, false));

    }


    @BindingAdapter({"imagem"})
    public static void setImagem(ImageView view, int imagem) {

        Context context = view.getContext();

        RequestOptions options = new RequestOptions()
                //.placeholder(defaultImageUrl)
                .error(R.drawable.ic_launcher_foreground);

        Glide.with(context)
                .setDefaultRequestOptions(options)
                .load(imagem)
                .into(view);

    }



    @BindingAdapter({"tipos", "id"})
    public static void setTipos(MaterialSpinner view, List<Tipo> registos, int id) {

        if (registos == null)
            return;

        view.setItems(registos);

        if(id != 0) {

            for (int index = 0; index < registos.size(); ++index) {

                if(registos.get(index).id == id){
                    view.setSelectedIndex(index);
                    break;
                }
            }
        }
    }



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
