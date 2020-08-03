package com.vvm.sh.databinding;

import android.graphics.Bitmap;
import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.vvm.sh.util.metodos.ImagemUtil;

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

        view.setImageBitmap(Bitmap.createScaledBitmap(ImagemUtil.converter(imagem), 900, 300, false));

    }


}
